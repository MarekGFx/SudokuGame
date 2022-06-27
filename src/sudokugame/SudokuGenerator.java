package sudokugame;

import javax.swing.*;

import java.awt.*;

import static java.lang.Math.random;

public class SudokuGenerator {

    private  void initialiser(JTextField[][]K){
        int i;int j;int row;int column;int v;
        for (i=0;i<9;i++){K[0][i].setText(Integer.toString(i+1));
            K[0][i].setBackground(Color.white);}
        for (j=9;j<81;j++){
            row=j/9;
            column=j%9;
            if(row%3==0) v=1; else v=0;
            K[row][column].setText(Integer.toString((Integer.parseInt(K[row-1][column].getText())+(2+v))%9+1));
            K[row][column].setBackground(Color.white);
        }
    }

    private void permuterInRow(JTextField[][]K,int L1, int L2){
        int x;
        for (int i=0;i<9;i++){
            x = Integer.parseInt(K[L2][i].getText());
            K[L2][i].setText(K[L1][i].getText());
            K[L1][i].setText(Integer.toString(x));
        }
    }

    private void permuterInColumn(JTextField[][]K,int C1, int C2){
        int y;
        for (int i=0;i<9;i++){
            y = Integer.parseInt(K[i][C2].getText());
            K[i][C2].setText(K[i][C1].getText());
            K[i][C1].setText(Integer.toString(y));
        }
    }

    private boolean sameNumbersInRow(JTextField[][]K,int x, int y, int i){
        if((K[x][i].getText().equals(K[y][0].getText()))||
                (K[x][i].getText().equals(K[y][1].getText()))||
                (K[x][i].getText().equals(K[y][2].getText()))) return true;
        return false;
    }

    private boolean sameNumbersInColumn (JTextField[][]K,int x, int y, int i){
        if((K[i][x].getText().equals(K[0][y].getText()))||
                (K[i][x].getText().equals(K[1][y].getText()))||
                (K[i][x].getText().equals(K[2][y].getText()))) return true;
        return false;
    }
//kasowanie pół po wygenerowaniu planszy
    private void eraser(JTextField[][]K,int x){
        int c;int col;
        for (int i=0;i<9;i++){
            c=0;
            int v =(int)(x*random())+3;
            while(c<v){
                col=(int)(9.0*random());
                if (!(K[i][col].getText().equals(""))) {
                    K[i][col].setText("0");
                    c++;
                }
            }
        }
        setEditableFields(SudokuFrame.textFields);
    }

    private void  setEditableFields(JTextField[][]K){
        int i,j;
        for (i=0;i<9;i++){
            for (j=0;j<9;j++) {
                if (K[i][j].getText().equals("0")){
                    K[i][j].setText("");
                    K[i][j].setEditable(true);
                }
                else K[i][j].setEditable(false);
            }
        }
    }

    private void creatorCopyTextFields(JTextField[][]K){
        int i,j;
        for (i=0;i<9;i++){
            for (j=0;j<9;j++) {
                K[i][j]=new JTextField();
                try{
                    K[i][j].setText("0");  }
                catch(NullPointerException e){}
            } }
    }
//kopia wygenrowanej planszy sudoku, do której porównuje koncowe ułożenie// Wady - jedno rozwiązanie sudoku
    private void copySudokuTextFieldsToNewCopyTextFields(JTextField [][]K, JTextField[][]L){
        int i,j;
        for (i=0;i<9;i++){
            for (j=0;j<9;j++) {
                try{
                    L[i][j].setText(K[i][j].getText());
                }
                catch(NullPointerException e){}
            }   }
    }

    private void generateSudokuFields(JTextField[][] M, int x, int y){
        int cl=0;int cc=0;
        initialiser(M);
        do{
            int L1 = (int)(9.0*random());
            int L2 = (int)(9.0*random());
            int C1 = (int)(9.0*random());
            int C2 = (int)(9.0*random());
            if ((L1/3==L2/3)&&(L1!=L2)||((sameNumbersInRow(M,L1,L2,0))&&((sameNumbersInRow(M,L1,L2,1)) &&
                    ((sameNumbersInRow(M,L1,L2,2))))))
            {
                permuterInRow(M,L1,L2);cl++;
            }
            if ((C1/3==C2/3)&&(C1!=C2)||((sameNumbersInColumn(M,C1,C2,0))&&((sameNumbersInColumn(M,C1,C2,1)) &&
                    ((sameNumbersInColumn(M,C1,C2,2))))))
            {
                permuterInColumn(M,C1,C2);cc++;
            }
        }
        while((cl<=x)||(cc<=x));
        creatorCopyTextFields(SudokuFrame.copyTextFields);
        copySudokuTextFieldsToNewCopyTextFields(M,SudokuFrame.copyTextFields);
        eraser(M,y);
        SudokuFrame.sudokuPanel.setVisible(true);
    }

    public void generator(JTextField[][] M, int x, int y ){
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        sudokuGenerator.generateSudokuFields(M,x,y);



    }

}
