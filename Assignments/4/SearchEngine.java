import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.ArrayList;
// Zenghao (Mike) Gao
public class SearchEngine {
	public HashMap<String, ArrayList<String>> wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception {
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}

	/*
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 *
	 * 	This method will fit in about 30-50 lines (or less)
	 */

	public void crawlAndIndex(String url) throws Exception {
		ArrayList<String> stack = new ArrayList<>();
		stack.add(url);
		internet.addVertex(url);
		internet.setVisited(url, true);
		while (!stack.isEmpty()) {
			String cur = stack.remove(stack.size() - 1);
			ArrayList<String> content = this.parser.getContent(cur);
			content.stream().distinct().forEach(w -> {
				// if it doesn't contain the word, add the arraylist
				String wd = w.toLowerCase();
				wordIndex.putIfAbsent(wd, new ArrayList<>());
				wordIndex.get(wd).add(cur);
			});

			for (String l : parser.getLinks(cur)) {
				if (!internet.getVisited(l)) {
					internet.addVertex(l);
					internet.setVisited(l, true);
					stack.add(l);
				}
				internet.addEdge(cur, l);
			}
		}

	}


	/*
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex().
	 * To implement this method, refer to the algorithm described in the
	 * assignment pdf.
	 *
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		// TODO : Add code here
		int size = internet.getVertices().size();
		ArrayList<String> vert = internet.getVertices();
		ArrayList<Double> prev;
		ArrayList<Double> next = new ArrayList<Double>(size);
		for (String v : vert) {
			internet.setPageRank(v, 1.0);
			next.add(1.0);
		}

		boolean converge = false;
		while (!converge) {
			prev = next;
			next = computeRanks(vert);
			for (int i = 0; i < size; i++) {
				internet.setPageRank(vert.get(i), next.get(i));
			}
			converge = true;
			for (int i = 0; i < size; i++) {
				if (Math.abs(prev.get(i) - next.get(i)) >= epsilon) {
					converge = false;
					break;
				}
			}
		}
	}

	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls.
	 * Note that the double in the output list is matched to the url in the input list using
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		// TODO : Add code here
		ArrayList<Double> ranksList = new ArrayList<>();
		for (String v : vertices) {
			double rank = 0;
			for (String linksToPg : internet.getEdgesInto(v)) {
				rank += internet.getPageRank(linksToPg) / internet.getOutDegree(linksToPg);
			}
			ranksList.add(rank * 0.5 + 0.5);
		}
		return ranksList;
	}

	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 *
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {
		// TODO: Add code here
		HashMap<String, Double> urls = new HashMap<>();
		ArrayList<String> queryLst = wordIndex.get(query);
		if (queryLst == null) return new ArrayList<String>();
		for (String links : queryLst) {
			urls.put(links, internet.getPageRank(links));
		}

		return Sorting.fastSort(urls);
	}
}


