//change from list to arraylist, add method, remove method
public class DigitalLibrary {

	private List<T> LibraryCollection; 
	
	public DigitalLibrary() {
		this.LibraryCollection = new ArrayList<>();
	}
	
	public static class ItemNotFoundException extends Exception {

		private static final long serialVersionUID = 1L;

		public ItemNotFoundException(String message) {
            super(message);
        }
    }

    public int subtract(int a, int b) {
        int diff = a - b;
        this.result = diff;
        System.out.println("Diff is: " + diff);
        return diff;
    }

        public void printLibrary() {
        for (T item : LibraryCollection) {
            System.out.println(item.toString());
            System.out.println();
        }
    }
}