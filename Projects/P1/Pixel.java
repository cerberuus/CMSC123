public class Pixel{
	private short red, green, blue;

	Pixel(){ // pixel with rgb = 0,0,0; default with no parameters
		this.red = 0;
		this.green = 0;
		this.blue = 0;
	}

	Pixel(short red, short green, short blue){ 
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public short setRed(short red){
		return this.red = red;
	}

	public short setGreen(short green){
		return this.green = green;
	}

	public short setBlue(short blue){
		return this.blue = blue;
	}

	public short getRed(){
		return this.red;
	}

	public short getGreen(){
		return this.green;
	}

	public short getBlue(){
		return this.blue;
	}
}