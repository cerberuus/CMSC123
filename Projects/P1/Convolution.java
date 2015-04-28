public class Convolution{
	int[][] matrixSobelX = new int[][]{
            {1,     0,  -1},
            {2,     0,  -2},
            {1,     0,  -1}
    };
    int[][] matrixSobelY = new int[][]{
            {-1,    -2,     -1},
            {0,     0,      0},
            {1,     2,      1}
    };

    public short matrixOperation(Pixel image[][], int x, int y){
    	//if normal matrix operation;; for 9 matrix
    	//int x is for width
    	//int y is for height
    	//first index width;; second height;;; marker for sane coding

    	Pixel newImage[][] = new Pixel[x][y];

    	for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++)
                    newImage[i][j] += matrixSobelX[i][k] * image[k][j];
        return short(newImage);


    }
}