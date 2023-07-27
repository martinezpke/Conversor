package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;


import java.awt.*;
import java.awt.Font;
import java.awt.Color;

public class ViewApp extends JFrame {
    private JPanel content;
    private JTextField inputCurrencyTextField;
    private JTextField outputCurrencyTextField;
    private JComboBox<String> currencyComboBox;
    private String[] currencies = {"Dolar estadounidense", "Euro", "Peso colombiano"};
    private String valueInputCurrency = "1";
    private String CountryInput = "Dolar estadounidense";
    private String valueOutputCurrency = "3.963,75";
    private String CountryOutput = "Peso colombiano";


    public static void main(String[] args) {
        ViewApp frame = new ViewApp();
        frame.setVisible(true);
    }

    private ViewApp() {
        Font font = new Font("Roboto,HelveticaNeue,Arial,sans-serif", Font.PLAIN, 14);
        /*
         * Ventana del programa del converso
         */

        setTitle("Conversor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);
    
        content = new JPanel();
        content.setToolTipText("");
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
        content.setLayout(new MigLayout("", "[grow][10px]", "[20px][20px][20px]"));
        content.setBackground(Color.decode("#202124")); // Color de fondo   
        
		setContentPane(content);

        JLabel ResultLabelTop = new JLabel(valueInputCurrency + " " + CountryInput + " Es igual a");
        ResultLabelTop.setForeground(Color.decode("#9aa0a6"));
		ResultLabelTop.setFont(font);
		content.add(ResultLabelTop, "cell 0 0, wrap");

        String labelText = "<html><div line-height: 1.58 >" + valueOutputCurrency +" "+CountryOutput + "</div></html>";
        JLabel ResultLabelButton = new JLabel(labelText);
        ResultLabelButton.setFont(new Font(font.getName(), Font.PLAIN, 36)); // Establecer tamaño de fuente más grande
        ResultLabelButton.setForeground(Color.decode("#e8eaed"));
		content.add(ResultLabelButton, "cell 0 1");

        inputCurrencyTextField = new JTextField(valueInputCurrency);
        inputCurrencyTextField.setFont(font);
        inputCurrencyTextField.setForeground(Color.decode("#e8eaed"));
        inputCurrencyTextField.setBackground(Color.decode("#444444"));
        content.add(inputCurrencyTextField, "cell 0 3, span 5, grow");

        currencyComboBox = new JComboBox<>(currencies);
        currencyComboBox.setFont(font);
        currencyComboBox.setForeground(Color.decode("#e8eaed"));
        currencyComboBox.setBackground(Color.decode("#444444"));
        content.add(currencyComboBox, "cell 0 3, span 5, grow");

        outputCurrencyTextField = new JTextField(valueOutputCurrency);
        outputCurrencyTextField.setForeground(Color.decode("#e8eaed"));
        outputCurrencyTextField.setBackground(Color.decode("#444444"));
        content.add(outputCurrencyTextField, "cell 0 4, span 5, grow");

        currencyComboBox = new JComboBox<>(currencies);
        currencyComboBox.setFont(font);
        currencyComboBox.setForeground(Color.decode("#e8eaed"));
        currencyComboBox.setBackground(Color.decode("#444444"));
        content.add(currencyComboBox, "cell 0 4, span 5, grow");

    }
}
