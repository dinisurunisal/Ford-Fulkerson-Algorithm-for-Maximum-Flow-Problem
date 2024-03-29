import java.util.Random;
import java.util.Scanner;

public class Modifymat {
    // Java Code For A Boolean Matrix Question

        public static void modifyMatrix(int mat[ ][ ], int R, int C)
        {
            int row[ ]= new int [R];
            int col[ ]= new int [C];
            int i, j;

            /* Initialize all values of row[] as 0 */
            for (i = 0; i < R; i++)
            {
                row[i] = 0;
            }


            /* Initialize all values of col[] as 0 */
            for (i = 0; i < C; i++)
            {
                col[i] = 0;
            }


		/* Store the rows and columns to be marked as
		1 in row[] and col[] arrays respectively */
            for (i = 0; i < R; i++)
            {
                for (j = 0; j < C; j++)
                {
                    if (mat[i][j] == 1)
                    {
                        row[i] = 1;
                        col[j] = 1;
                    }
                }
            }

		/* Modify the input matrix mat[] using the
		above constructed row[] and col[] arrays */
            for (i = 0; i < R; i++)
            {
                for (j = 0; j < C; j++)
                {
                    if ( row[i] == 1 || col[j] == 1 )
                    {
                        mat[i][j] = 1;
                    }
                }
            }
        }

        /* A utility function to print a 2D matrix */
        public static void printMatrix(int mat[ ][ ], int R, int C)
        {
            int i, j;
            for (i = 0; i < R; i++)
            {
                for (j = 0; j < C; j++)
                {
                    System.out.print(mat[i][j]+ " ");
                }
                System.out.println();
            }
        }

        /* Driver program to test above functions */
        public static void main(String[] args)
        {
            int mat[ ][ ] = { {1, 0, 0, 1},
                    {0, 0, 1, 0},
                    {0, 0, 0, 0},};

            System.out.println("Matrix Intially");

            printMatrix(mat, 3, 4);

            modifyMatrix(mat, 3, 4);
            System.out.println("Matrix after modification n");
            printMatrix(mat, 3, 4);

        }
}



//    Scanner scan = new Scanner(System.in);
//
//    Random rd = new Random(); // creating Random object
//            System.out.println("Enter The Number Of Matrix Rows");
//
//                    int matrixRow = scan.nextInt();
//                    int matrixCol = matrixRow;
//                    int[][] arr = new int[matrixRow][matrixCol];
//
//                    for (int i = 0; i < matrixRow; i++)
//        {
//        for (int j = 0; j < matrixCol; j++)
//        {
//        arr[i][j] = rd.nextInt(11);
//        }
//        }
//        System.out.println("Your Matrix is : ");
//
//        for (int i = 0; i < matrixRow; i++)
//        {
//        for (int j = 0; j < matrixCol; j++)
//        {
//        System.out.print(arr[i][j]+"\t");
//        }
//
//        System.out.println();
//        }