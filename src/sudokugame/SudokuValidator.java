package sudokugame;

import javax.swing.*;

public class SudokuValidator {

    private void validInRow(JTextField[][]K, JTextField[][]L, int x){
        boolean valid = true; int i;
        for (i=0; i<9;i++){
            if(!(K[x][i].getText().equals(L[x][i].getText()))) valid = false;
        }
        if(valid==true){
            for ( i=0; i<9;i++){
              //  K[x][i].setBackground(Color.green);
                K[x][i].setEditable(false);
            }
        }
    }

   private void validInColumn(JTextField[][]K, JTextField[][]L, int x){
        boolean valid = true; int i;
        for (i=0; i<9;i++){
            if(!(K[i][x].getText().equals(L[i][x].getText()))) valid = false;
        }
        if(valid==true){
            for ( i=0; i<9;i++){
              //  K[i][x].setBackground(Color.green);
                K[i][x].setEditable(false);
            }
        }
    }

    private void validInSquare3X3(JTextField[][]K,JTextField[][]L,int line,int col){
        int l=0;int c =0;boolean valid=true;int row, column;
        switch(line){
            case 0:case 1: case 2:{
                switch (col){
                    case 0:case 1: case 2:{ l=0;c=0;break;}
                    case 3:case 4: case 5: { l=0;c=3;break;}
                    case 6:case 7: case 8:{ l=0;c=6;break;}
                }break;
            }
            case 3:case 4: case 5:{
                switch (col){
                    case 0:case 1: case 2:{ l=3;c=0;break;}
                    case 3:case 4: case 5: { l=3;c=3;break;}
                    case 6:case 7: case 8:{ l=3;c=6;break;}
                }break;
            }
            case 6:case 7: case 8:{
                switch (col){
                    case 0:case 1: case 2:{ l=6;c=0;break;}
                    case 3:case 4: case 5: { l=3;c=3;break;}
                    case 6:case 7: case 8:{ l=6;c=6;break;}
                }break;
            }
        }
        for (int i = 0; i<9;i++){
            row=(i/3)+l;
            column = (i%3)+c;
            if(!(K[row][column].getText().equals(L[row][column].getText())))
                valid= false;
        }
        if (valid==true){
            for (int i=0;i<9;i++){
                row=(i/3)+l;
                column = (i%3)+c;
              //  K[row][column].setBackground(Color.green);
                K[row][column].setEditable(false);
            }
        }
    }

// porównanie wypełnionej planszy do kopii
    public boolean checkEquals(JTextField[][]K,JTextField[][]L){
        for (int i =0;i<9;i++){
            for (int j =0;j<9;j++){
                if(!(K[i][j].getText().equals(L[i][j].getText()))) return false;
            }
        }
        return true;
    }

    public void validSudokuFields(JTextField[][]K,JTextField[][]L, int x, int y ,int line,int col){
        SudokuValidator sudokuValidator = new SudokuValidator();
        sudokuValidator.validInRow(K,L,x);
        sudokuValidator.validInColumn(K,L,y);
        sudokuValidator.validInSquare3X3(K,L,line,col);
    }

}
