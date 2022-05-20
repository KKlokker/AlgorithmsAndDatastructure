package huffman;

public class Element {

		  private int key;
		  private Object data;

		  public Element(int i, Object o) {
				this.key = i;
				this.data = o;
			}

		  public int getKey() {
				return key;
		  }

		  public Object getData() {
				return data;
		  }
}
