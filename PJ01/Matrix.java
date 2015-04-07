public class Matrix{
	
	/** This class is for a less wordy code for the operations involving matrix. Operations 
	 * include basic elementary matrix operation such as matrix addition and matrix multiplication.
	 */

	Pixel[][] mat;

    public Matrix(Pixel[][] a)
    {
        mat = a;
    }

    public Matrix()
    {
        mat = new Pixel[3][3];
    }
    
    public Matrix product(int[][] b)
    {
        Matrix result = new Matrix();

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                result.mat[i][j] = mat[i][j].multiply(b[i][j]);                
            }
        }
        return result;
    }

}