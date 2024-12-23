package bsu.rfe.java.group8.lab2.Kuzmich.varA7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    private Double sum = 0.0;

    public Double calculate1(Double x, Double y) {
        return x * x + y * y;
    }

    public Double calculate2(Double x, Double y) {
        return x * x * x + 1 / y;
    }

    public Double calculate3(Double x, Double y, Double z) {
        return Math.pow(Math.log(1 + z), 2) + Math.cos(Math.PI * Math.pow(y, 3))
                / Math.pow(Math.cos(Math.exp(x))
                + Math.pow(x, 1.0 / 7) + Math.exp(x * x), Math.sin(x));
    }

    public Double calculate4(Double x, Double y, Double z) {
        return Math.pow(Math.sin(Math.pow(z, y)), 2) / Math.sqrt(1 + Math.pow(x, 3));
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);

        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        addRadioButton("Формула 3", 3);
        addRadioButton("Формула 4", 4);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));

        Box boxForX = Box.createHorizontalBox();
        boxForX.add(labelForX);
        boxForX.add(Box.createHorizontalStrut(10));
        boxForX.add(textFieldX);

        Box boxForY = Box.createHorizontalBox();
        boxForY.add(Box.createHorizontalGlue());
        boxForY.add(labelForY);
        boxForY.add(Box.createHorizontalStrut(10));
        boxForY.add(textFieldY);
        boxForY.add(Box.createHorizontalGlue());

        Box boxForZ = Box.createHorizontalBox();
        boxForZ.add(labelForZ);
        boxForZ.add(Box.createHorizontalStrut(10));
        boxForZ.add(textFieldZ);

        hboxVariables.add(boxForX);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(boxForY);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(boxForZ);


        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1) result = calculate1(x, y);
                    else if (formulaId == 2) result = calculate2(x, y);
                    else if (formulaId == 3) result = calculate3(x, y, z);
                    else result = calculate4(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                sum = 0.0;
                textFieldResult.setText("0");
            }
        });

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldResult.getText());
                    Double result;
                    if (formulaId == 1) result = calculate1(x, y);
                    else if (formulaId == 2) result = calculate2(x, y);
                    else if (formulaId == 3) result = calculate3(x, y, z);
                    else result = calculate4(x, y, z);
                    sum += result;
                    textFieldResult.setText(sum.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonMPlus);
        hboxButtons.add(Box.createHorizontalGlue());

        Box contentBox = Box.createVerticalBox();
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
