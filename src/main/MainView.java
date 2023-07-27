package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.JSONObject;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.math.BigDecimal;

import utils.CurrencyConvert;

public class MainView extends JFrame {

    private JPanel contentOption;
    private JPanel cardPanel;
    private JPanel currencyConverter;
    private JPanel badgeView;
    private CardLayout cardLayout;
    private CurrencyConvert apiConsumer = new CurrencyConvert();

    private BigDecimal base = BigDecimal.ZERO;
    private BigDecimal to = BigDecimal.ZERO;
    private String disclaimer = "";
    private String CountryInput = "";
    private String CountryOutput = "";
    private boolean userUpdate = true;

    public static void main(String[] args) {
        MainView frame = new MainView();
        frame.setVisible(true);
    }

    private MainView() {

        setTitle("Conversor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        contentOption = new JPanel(new MigLayout("center, center", "[center][center]"));
        contentOption.setBackground(Color.decode("#202124")); // Color de fondo

        // Buttons
        JButton buttonBadge = new JButton("Divisas");
        JButton buttonTemperature = new JButton("Temperatura");

        contentOption.add(buttonBadge, "cell 0 0");
        contentOption.add(buttonTemperature, "cell 0 1");

        cardPanel.add(contentOption, "mainView"); // Add the main view to the card panel

        // Create and add the badge view to the card panel
        badgeView = new JPanel(new MigLayout(""));

        // Button back
        JButton backButton = new JButton("Atrás");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "mainView");
                pack();
            }
        });
        badgeView.add(backButton, "cell 0 0");
        badgeView.setBackground(Color.decode("#202124"));

        // Label
        JLabel titleBadge = new JLabel("Elige las monedas para hacer una conversion");
        titleBadge.setForeground(Color.decode("#e8eaed"));
        badgeView.add(titleBadge, "cell 0 1 0 0");

        String[] currencys = { "    -- Elige una opción --", "USD", "JPY", "EUR", "ARS", "COP", "MXN" };
        JComboBox<String> baseCurrencyBox = new JComboBox<>(currencys);
        badgeView.add(baseCurrencyBox, "cell 0 3");

        JComboBox<String> toCurrencyBox = new JComboBox<>(currencys);
        badgeView.add(toCurrencyBox, "cell 0 3");

        // button of converting currency
        JButton buttonConvert = new JButton("Convertir");
        buttonConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * String badgesConverter = baseCurrencyBox.getSelectedItem() + "-" +
                 * toCurrencyBox.getSelectedItem();
                 */
                viewCurrency(currencys, backButton, baseCurrencyBox, toCurrencyBox, buttonConvert);
                cardLayout.show(cardPanel, "currencyConverter");

            }
        });

        buttonBadge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // cardLayout.show(cardPanel, "badgeView");
                viewCurrency(currencys, backButton, baseCurrencyBox, toCurrencyBox, buttonConvert);
                cardLayout.show(cardPanel, "currencyConverter");
            }
        });

        badgeView.add(buttonConvert, "cell 0 4");

        cardPanel.add(badgeView, "badgeView");

        setContentPane(cardPanel);

        pack();
        /* setSize(400, 300); */
        setLocationRelativeTo(null);
    }

    private String formatCurrency(BigDecimal amount) {
        // Define el formato deseado
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        // Formatea el valor de la moneda
        String formattedCurrency = decimalFormat.format(amount);

        return formattedCurrency;
    }

    public void viewCurrency(String[] currencys, JButton backButton, JComboBox baseCurrencyBox, JComboBox toCurrencyBox,
            JButton currency) {

        Font font = new Font("Roboto,HelveticaNeue,Arial,sans-serif", Font.PLAIN, 14);
        try {
            // String data = apiConsumer.getCurrency(badgesConverter);

            /* System.out.println(data); */

            /*
             * // Parsear la respuesta JSON
             * JSONObject jsonObject = new JSONObject(data);
             * 
             * // Obtener cada valor por clave
             * String disclaimer = jsonObject.getString("disclaimer");
             * String name = jsonObject.getString("name");
             * base = jsonObject.getBigDecimal("base");
             * to = jsonObject.getBigDecimal("To");
             * 
             * // aqui vamos a separar los nombres de cada pais
             * String[] spliCurrency = name.split(" a ");
             * String[] spliCountry = spliCurrency[0].split(" ");
             * CountryInput = spliCountry[1];
             * CountryOutput = spliCurrency[1];
             */

            // view converter
            currencyConverter = new JPanel(); // Crear el panel una sola vez
            currencyConverter.setToolTipText("");
            currencyConverter.setBorder(new EmptyBorder(5, 5, 5, 5));
            currencyConverter.setLayout(new MigLayout("", "[grow][10px]", "[20px][20px][20px]"));
            currencyConverter.setBackground(Color.decode("#202124")); // Color de fondo

            currencyConverter.add(backButton, "cell 0 0");

            JLabel ResultLabelTop = new JLabel(base + " " + CountryInput + " Es igual a");
            ResultLabelTop.setForeground(Color.decode("#9aa0a6"));
            ResultLabelTop.setFont(font);
            currencyConverter.add(ResultLabelTop, "cell 0 1, wrap");

            String labelText = "<html><div line-height: 1.58 >" + formatCurrency(to) + " " + CountryOutput
                    + "</div></html>";
            JLabel ResultLabelButton = new JLabel(labelText);
            ResultLabelButton.setFont(new Font(font.getName(), Font.PLAIN, 36));
            ResultLabelButton.setForeground(Color.decode("#e8eaed"));
            currencyConverter.add(ResultLabelButton, "cell 0 2");

            // label del disclaimer
            JLabel labelDisclaimer = new JLabel(disclaimer);
            labelDisclaimer.setForeground(Color.decode("#9aa0a6"));
            labelDisclaimer.setFont(font);
            currencyConverter.add(labelDisclaimer, "cell 0 3");

            JTextField inputCurrencyTextField = new JTextField(String.valueOf(base));
            inputCurrencyTextField.setFont(font);
            inputCurrencyTextField.setForeground(Color.decode("#e8eaed"));
            inputCurrencyTextField.setBackground(Color.decode("#444444"));
            currencyConverter.add(inputCurrencyTextField, "cell 0 4, span 5, grow");

            JComboBox<String> currencyComboBoxTop = new JComboBox<>(currencys);
            currencyComboBoxTop.setSelectedItem(currencys[baseCurrencyBox.getSelectedIndex()]);
            currencyComboBoxTop.setFont(font);
            currencyComboBoxTop.setForeground(Color.decode("#e8eaed"));
            currencyComboBoxTop.setBackground(Color.decode("#444444"));
            currencyConverter.add(currencyComboBoxTop, "cell 0 4, span 5, grow");

            JTextField outputCurrencyTextField = new JTextField(formatCurrency(to));
            outputCurrencyTextField.setForeground(Color.decode("#e8eaed"));
            outputCurrencyTextField.setBackground(Color.decode("#444444"));
            currencyConverter.add(outputCurrencyTextField, "cell 0 5, span 5, grow");

            JComboBox<String> currencyComboBoxButton = new JComboBox<>(currencys);
            currencyComboBoxButton.setSelectedItem(currencys[toCurrencyBox.getSelectedIndex()]);
            currencyComboBoxButton.setFont(font);
            currencyComboBoxButton.setForeground(Color.decode("#e8eaed"));
            currencyComboBoxButton.setBackground(Color.decode("#444444"));
            currencyConverter.add(currencyComboBoxButton, "cell 0 5, span 5, grow");

            currencyComboBoxTop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userUpdate = false;
                    try {
                        String badgesConverter = currencyComboBoxTop.getSelectedItem() + "-"
                                + currencyComboBoxButton.getSelectedItem();
                        String data = apiConsumer.getCurrency(badgesConverter);

                        JSONObject jsonObject = new JSONObject(data);

                        to = jsonObject.getBigDecimal("To");
                        base = jsonObject.getBigDecimal("base");
                        String disclaimer = jsonObject.getString("disclaimer");
                        String name = jsonObject.getString("name");

                        // aquí vamos a separar los nombres de cada pais
                        String[] spliCurrency = name.split(" a ");
                        String[] spliCountry = spliCurrency[0].split("De");
                        CountryInput = spliCountry[1];
                        CountryOutput = spliCurrency[1];

                        String labelText = "<html><div line-height: 1.58 >" + formatCurrency(to) + " " + CountryOutput
                                + "</div></html>";

                        outputCurrencyTextField.setText(String.valueOf(to));
                        inputCurrencyTextField.setText(String.valueOf(base));
                        ResultLabelButton.setText(labelText);
                        ResultLabelTop.setText(base + " " + CountryInput + " Es igual a");
                        labelDisclaimer.setText(disclaimer);

                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                    userUpdate = true;
                    pack();
                }
            });

            currencyComboBoxButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userUpdate = false;
                    try {
                        String badgesConverter = currencyComboBoxTop.getSelectedItem() + "-"
                                + currencyComboBoxButton.getSelectedItem();
                        String data = apiConsumer.getCurrency(badgesConverter);

                        JSONObject jsonObject = new JSONObject(data);

                        base = jsonObject.getBigDecimal("base");
                        to = jsonObject.getBigDecimal("To");
                        String disclaimer = jsonObject.getString("disclaimer");
                        String name = jsonObject.getString("name");

                        // aqui vamos a separar los nombres de cada pais
                        String[] spliCurrency = name.split(" a ");
                        String[] spliCountry = spliCurrency[0].split("De");
                        CountryInput = spliCountry[1];
                        CountryOutput = spliCurrency[1];

                        String labelText = "<html><div line-height: 1.58 >" + formatCurrency(to) + " " + CountryOutput
                                + "</div></html>";

                        outputCurrencyTextField.setText(String.valueOf(to));
                        inputCurrencyTextField.setText(String.valueOf(base));
                        ResultLabelButton.setText(labelText);
                        ResultLabelTop.setText(base + " " + CountryInput + " Es igual a");
                        labelDisclaimer.setText(disclaimer);

                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                    userUpdate = true;
                    pack();
                }
            });

            inputCurrencyTextField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateOutputCurrency();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // Código para manejar cambios en atributos del documento (generalmente no se
                    // usa en JTextField)
                }

                private void updateOutputCurrency() {
                    try {
                        if (userUpdate) {
                            BigDecimal baseGet = new BigDecimal(inputCurrencyTextField.getText());
                            BigDecimal toGet = to;
                            BigDecimal newTo = baseGet.multiply(toGet);
                            outputCurrencyTextField.setText(String.valueOf(newTo));

                            System.out.println(baseGet + " " + toGet);
                        }
                    } catch (NumberFormatException ex) {
                        // Manejar la excepción en caso de que el texto ingresado no sea un número
                        // válido.
                        System.out.println("Error: El texto ingresado no es un número válido.");
                        outputCurrencyTextField.setText("0"); // O un valor predeterminado en caso de error.
                    }
                }
            });
            /*
             * outputCurrencyTextField.getDocument().addDocumentListener(new
             * DocumentListener() {
             * 
             * @Override
             * public void insertUpdate(DocumentEvent e) {
             * updateOutputCurrency();
             * }
             * 
             * @Override
             * public void removeUpdate(DocumentEvent e) {
             * }
             * 
             * @Override
             * public void changedUpdate(DocumentEvent e) {
             * // Código para manejar cambios en atributos del documento (generalmente no se
             * // usa en JTextField)
             * }
             * 
             * private void updateOutputCurrency() {
             * try {
             * if (userUpdate) {
             * BigDecimal toGet = new BigDecimal(inputCurrencyTextField.getText());
             * double aux = 1/toGet.doubleValue();
             * BigDecimal newBase = new BigDecimal(aux).multiply(toGet);
             * inputCurrencyTextField.setText(String.valueOf(newBase));
             * 
             * }
             * } catch (NumberFormatException ex) {
             * // Manejar la excepción en caso de que el texto ingresado no sea un número
             * // válido.
             * System.out.println("Error: El texto ingresado no es un número válido.");
             * inputCurrencyTextField.setText("0"); // O un valor predeterminado en caso de
             * error.
             * }
             * }
             * });
             */

            cardPanel.remove(currencyComboBoxButton);
            cardPanel.add(currencyConverter, "currencyConverter");
            cardPanel.revalidate();
            cardPanel.repaint();

        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }

        pack();
    }

}
