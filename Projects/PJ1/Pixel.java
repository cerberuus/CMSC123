public class ImagePixelRGBA {
	
	public short r;
	public short g;
	public short b;

	/**
	 *	ImagePixelRGBA Constructor
	 *	r references to red
	 *	g references to green
	 *	b references to blue
	 */

	/*ImagePixelRGBA(){
	 *	red = 0;
	 *	green = 0;
	 *	blue = 0;
	 *	}
	 */

	/**
	 *	RGBA get PixelRGBA.
	 */

	Pixel(){
		this.r = 0;
		this.g = 0;
		this.b = 0;
	}

	private short getRed(){
		return r;
	}

	private short getGreen(){
		return g;
	}

	private short getBlue(){
		return b;
	}

	private short accessRGB(){
		
	}
}