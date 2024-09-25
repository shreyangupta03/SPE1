package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



// kzgbksjdfgb
// skjfgn

class MyCalculator extends JFrame{
    private static final Logger logger2 = LogManager.getLogger(MyCalculator.class);
    private boolean start = true;
    private double result = 0;
    private String command = "=";
    private JTextField jTextField;
    private JPanel jPanel = new JPanel();
    private JButton[] jButtons;

    boolean ongoing = false;
    public MyCalculator() {
        logger2.info("Start of Execution calc.");

        this.setTitle("Calculator");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);

        jTextField = new JTextField(30);
        jTextField.setText("");
        jTextField.setEditable(true);
        this.add(jTextField,"North");
//test
        jPanel.setLayout(new GridLayout(5,7,3,3));
        String name[] = {
                "+/-","PI","1/X","C","/","*","Back","X^2","X^3",
                "X^y","7","8","9","-","X!","root(X)","X^1/3","4","5",
                "6","+","sin","cos","tan","1","2","3","%",
                "Base2","Base10","cot","time","0",".","="
        };
        //new test ngrok
        //new test ngrok
        //ngrok
        jButtons = new JButton[name.length];
        MyActionListener actionListener= new MyActionListener();

        for(int i = 0; i < name.length; i++) {

            jButtons[i] = new JButton(name[i]);
            jButtons[i].addActionListener(actionListener);

            jButtons[i].setBackground(Color.lightGray);
            if(name[i].equals("="))
                jButtons[i].setBackground(Color.RED);
            else if((int)name[i].charAt(0)>=48 && (int)name[i].charAt(0)<=57
                    && name[i].length() == 1)
                jButtons[i].setBackground(Color.WHITE);
            else if(name[i].equals("Back"))
                jButtons[i].setBackground(Color.GRAY);

            jPanel.add(jButtons[i]);
            logger2.info("Buttons added to the panel");

        }

        this.add(jPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            String input = e.getActionCommand();
            logger2.info(input);
            logger2.info("Start : " + start + " Result : " + result);
            if(start && !(input == "+" || input == "*" || input == "-" || input == "/" )) {

                if((int)input.charAt(0)>=48 && (int)input.charAt(0)<=57
                        && input.length() == 1 ) {
                    jTextField.setText(""+input);
                }
                if(input.equals("+/-")) {
                    jTextField.setText("-");
                }
                if(input.equals("PI")) {

                    jTextField.setText(""+Math.PI);
                }
                start = false;
                if(input.equals("C"))
                    jTextField.setText("");
            }

            else if((int)input.charAt(0)>=48 && (int)input.charAt(0)<=57
                    && input.length() == 1 || input.equals(".")){
                jTextField.setText(jTextField.getText()+input);
            }



            else if(input.equals("C")) {
                logger2.info("clear");
                result = 0;
                jTextField.setText("");
            }

            else if(input.equals("Back")) {
                logger2.info("backspace");
                if(jTextField.getText().length() > 0){
                    jTextField.setText(jTextField.getText().substring(0,jTextField.getText().length()-1));
                }
            }

            else if(input.equals("sin")) {
                logger2.info("sin");

                result = Math.sin(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            else if(input.equals("cos")) {
                logger2.info("cos");
                result = Math.cos(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            else if(input.equals("cot")) {
                logger2.info("cot");
                result = 1.0/Math.tan(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            else if(input.equals("tan")) {
                logger2.info("tan");
                result = Math.tan(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            else if(input.equals("Base2")) {
                String result2 = Integer.toBinaryString(Integer.parseInt(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(result2));
                start = true;
            }

            else if(input.equals("Base10")) {
                try {
                    String result2 = Integer.valueOf(jTextField.getText(),2).toString();
                    jTextField.setText(""+getPrettyNumber(result2));
                }catch(NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "", "Error!", JOptionPane.ERROR_MESSAGE);
                    throw new NumberFormatException("");
                }finally {
                    start = true;
                }

            }
            else if(input.equals("1/X")) {
                result = 1 / Double.parseDouble(jTextField.getText());
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            else if(input.equals("X^2")) {
                logger2.info("squaring");

                result = Math.pow(Double.parseDouble(jTextField.getText()), 2);
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            else if(input.equals("X^3")) {
                logger2.info("cubing");

                result = Math.pow(Double.parseDouble(jTextField.getText()), 3);
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            else if(input.equals("X!")) {
                if(Double.parseDouble(jTextField.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "negative", "Error!", JOptionPane.ERROR_MESSAGE);
                    jTextField.setText("negative");
                    start = true;
                    throw new IllegalArgumentException("negative");
                }else {
                    int sum;
                    sum = factorial(Integer.parseInt(jTextField.getText()));
                    jTextField.setText(Integer.toString(sum));
                    start = true;
                }

            }

            else if(input.equals("%")) {
                result = Double.parseDouble(jTextField.getText())/ 100.0;
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            else if(input.equals("root(X)")) {
                logger2.info("root");

                result = Math.sqrt(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            else if(input.equals("X^1/3")) {
                result = Math.pow(Double.parseDouble(jTextField.getText()),1.0/3);
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            else if(input.equals("time")) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jTextField.setText(df.format(System.currentTimeMillis()));
                start = true;
            }

            else {
                if(jTextField.getText()!="") start = false;
                if(!start) {

                    if(command.equals("+")) {
                        logger2.info("add "+result+" , " + jTextField.getText());
                        result = add(result,Double.parseDouble(jTextField.getText()));
                        logger2.info(result);
                    }
                    else if(command.equals("-")) {
                        logger2.info("sub "+result+" , " + jTextField.getText());
                        result = sub(result,Double.parseDouble(jTextField.getText()));
                        logger2.info(result);
                    }
                    else if(command.equals("*")) {
                        logger2.info("mult "+result+" , " + jTextField.getText());
                        result = mult(result,Double.parseDouble(jTextField.getText()));
                        logger2.info(result);
                    }
                    else if(command.equals("/")) {
                        if(Double.parseDouble(jTextField.getText()) != 0) {
                            logger2.info("div "+result+" , " + jTextField.getText());
                            result = div(result,Double.parseDouble(jTextField.getText()));
                            logger2.info(result);
                        }else {
                            jTextField.setText(""+"NAN");
                            JOptionPane.showMessageDialog(null, "NAN", "Error!", JOptionPane.ERROR_MESSAGE);
                            command = "=";
                            start = true;
                            throw new ArithmeticException("NAN");
                        }

                    }
                    else if(command.equals("=")) {
                        result = Double.parseDouble(jTextField.getText());
                        logger2.info("In buffer : "+ result);
                    }

                    else if(command.equals("X^y"))
                        result = Math.pow(result, Double.parseDouble(jTextField.getText()));
                    jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                    command = input;
                    logger2.info("Next op set to "+ command);
                    start = true;
                }
            }
        }
    }
    public double add(double res, double a){
        return a+res;
    }
    public double sub(double res, double a){
        return res - a;
    }
    public double mult(double res, double a){
        return a*res;
    }
    public double div(double res, double a){
        return res/a;
    }
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }
    public static int factorial(int num) {
        int sum = 1;
        for(int i = 1;i <= num; i++){
            sum *= i;
        }
        return sum;
    }
//TEST FOR GIT SCM POLLING
//    public static void main(String[] args) {
//        MyCalculator myCalculator = new MyCalculator();
//
//    }

}
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public double add(double res, double a){
        return a+res;
    }
    public double sub(double res, double a){
        return res - a;
    }
    public double mult(double res, double a){
        return a*res;
    }
    public double div(double res, double a){
        return res/a;
    }
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.˳
        System.out.println("Hello and welcome!");
        logger.info("Started main. calling calc.");
        // Press Ctrl+R or click the green arrow button in the gutter to run the code.

        //start is to keep track of the operations that need 2 operands like x^y or x*y or x-y etc
        //any other operations are just executed and printed.
        System.out.println("Enter 1 to allow gui Not advised on docker containers...0 for basic mode");
        Scanner ob = new Scanner(System.in);
        int ch = ob.nextInt();
        if(ch == 1){
            MyCalculator calc = new MyCalculator();
        }
        else{
            boolean flag = true;
            while(flag){
                System.out.println("Enter -1 to stop\n 1 : add \n 2 : Sub\n 3 : Mult\n 4 : div\n followed by 2 numbers");
                int c = ob.nextInt();
                if(c == -1) break;
                double a = ob.nextDouble();
                double b = ob.nextDouble();
                if(c == 1){
                    System.out.println(a+b);
                }
                if(c == 2){
                    System.out.println(a-b);
                }
                if(c == 3){
                    System.out.println(a*b);
                }
                if(c == 4){
                    System.out.println(a/b);
                }
            }
        }
    }
}