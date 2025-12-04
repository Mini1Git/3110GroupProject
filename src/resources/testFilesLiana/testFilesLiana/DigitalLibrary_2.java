//change from list to arraylist, add method, remove method
public class DigitalLibrary {

	private ArrayList<T> LibraryCollection; 
	
	public DigitalLibrary() {
		this.LibraryCollection = new ArrayList<>();
	}
	
	public static class ItemNotFoundException extends Exception {

		private static final long serialVersionUID = 1L;

		public ItemNotFoundException(String message) {
            super(message);
        }
    }
	
	public List<T> filterByGenre(String genre) {
        List<T> filteredGenre = new ArrayList<>();
        for (T item : LibraryCollection) {
            if (item.getGenre().equalsIgnoreCase(genre)) {
                filteredGenre.add(item);
            }
        }
        return filteredGenre;
    }

    

        public void printLibrary() {
        for (T item : LibraryCollection) {
            System.out.println(item.toString());
            System.out.println();
        }
    }
}
