package ka2.tesa.elements;

import ka2.tesa.Element;

public class Container extends Element {

	public Container(int x, int y,int id) {
		super(4, x, y, 0, id);
		this.setCapacity(Integer.MAX_VALUE);
	}

}
