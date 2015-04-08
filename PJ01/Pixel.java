public class Pixel{
	
	private short red, green, blue;

	public Pixel(){
		this.red = 0;
		this.green = 0;
		this.blue = 0;
	}
	public Pixel(short red, short green, short blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
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

	public short setRed(short red){
		return this.red = red;
	}

	public short setGreen(short green){
		return this.green = green;
	}

	public short setBlue(short blue){
		return this.blue = blue;
	}

	public String toString(int x, int y, short blue, short red, short green){
		// int x is for the width while int y is for the height

		String str = new String("");
		for(int i = 0; i < x; i++)
			for(int j = 0; j < y; j++){
				str += "R" + setRed(red);
				str += " G" + setGreen(green);
				str += " B" + setBlue(blue) + "\n";
			}

		return str;

	}

	public Pixel multiply(int x)
    {
        Pixel res = new Pixel();
        res.red = (short)(x*red);
        res.blue = (short)(x*blue);
        res.green = (short)(x*green);
        return res;

    }

    public short add(PixImage[][] pic, int x, int y){
    	Pixel pix = new Pixel(red, green, blue);

    	
    }
}