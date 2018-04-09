package com.accounting;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class AccountingWindow {
	public JFrame main;
	public JPanel content;
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "pharmacy_db";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String[] MONTHS = {"Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень", "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"};

    public AccountingWindow() throws IOException, SQLException, ClassNotFoundException, FontFormatException {
        DbUtils dbUtils = new DbUtils(DB_HOST, DB_NAME, USER_NAME, PASSWORD);
        initMainFrame(dbUtils);
    }

    private void setFont() {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/com/resources/font.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        Font monteserrat = new Font("Montserrat", Font.PLAIN, 18);
        UIManager.put("Label.font", monteserrat);
        UIManager.put("Button.font", monteserrat);
    }

    private void initMainFrame(DbUtils dbUtils) throws IOException, SQLException {
        setFont();
        main = new JFrame();

        Container contentPane = main.getContentPane();
        content = new JPanel();
        GridBagLayout contentGridBagLayout = new GridBagLayout();
        content.setLayout(contentGridBagLayout);
        content.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(content, BorderLayout.SOUTH);
        setUpContent(content, dbUtils, 0);

        main.pack();
    }

    private void setUpContent(JPanel content, DbUtils dbUtils, int activeMonth) throws IOException, SQLException {
        addButtons(content, 3, activeMonth, dbUtils);
        addSections(content);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.MONTH, -activeMonth);
        Date end = new Date(endCalendar.getTime().getTime());

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.MONTH, -activeMonth-1);
        Date start = new Date(startCalendar.getTime().getTime());

        ImageIcon pharmacyImage = loadImage("pharmacy.png");
        ResultSet summaryForPeriod = dbUtils.getSummaryForPeriod(start, end);
        int i = 0;
        while (summaryForPeriod.next()) {
            addPharmacy(content, i++, pharmacyImage, summaryForPeriod.getString("name"), summaryForPeriod.getDouble("revenues"), summaryForPeriod.getDouble("spendings"));
        }

        addLogo(content);
    }

    private void addLogo(JPanel content) throws IOException {
        JLabel logo = new JLabel(loadImage("logo.png"));
        GridBagConstraints logoGbc = new GridBagConstraints();
        logoGbc.gridwidth = 999;
        content.add(logo, logoGbc);
    }

    private void addButtons(final JPanel content, int buttonsNum, int active, final DbUtils dbUtils) {
        JPanel periodBtns = new JPanel();
        GridBagConstraints periodBtnGbc = new GridBagConstraints();
        periodBtnGbc.gridx = 0;
        periodBtnGbc.gridy = 1;
        periodBtnGbc.gridwidth = 999;
        periodBtnGbc.insets = new Insets(10, 0, 10, 0);

        for(int i = buttonsNum-1; i >= 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -i);
            Date date = new Date(calendar.getTime().getTime());

            JButton periodBtn = new JButton(MONTHS[date.getMonth()] + " " + (1900+date.getYear()));
            periodBtn.setBackground(i == active ? Color.BLACK : Color.WHITE);
            periodBtn.setForeground(i == active ? Color.WHITE : Color.BLACK);
            periodBtn.setOpaque(i == active);
            periodBtn.setFocusPainted(false);
            periodBtn.setBorder(new RoundedBorder(5));

            periodBtns.add(periodBtn);

            final int finalI = i;
            periodBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    content.removeAll();
                    try {
                        setUpContent(content, dbUtils, finalI);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    content.revalidate();
                    content.repaint();
                }
            });
        }

        content.add(periodBtns, periodBtnGbc);
    }

    private void addSections(JPanel content) {
        Font montesseratBold = new Font("Montesserat", Font.BOLD, 18);

        JLabel revenuesLabel = new JLabel("Доходи");
        revenuesLabel.setFont(montesseratBold);
        GridBagConstraints revenuesGbc = new GridBagConstraints();
        revenuesGbc.gridy = 4;
        revenuesGbc.gridwidth = 999;
        revenuesGbc.insets = new Insets(25, 0, 15, 0);
        content.add(revenuesLabel, revenuesGbc);

        JLabel spendingsLabel = new JLabel("Витрати");
        spendingsLabel.setFont(montesseratBold);
        GridBagConstraints spendingsGbc = new GridBagConstraints();
        spendingsGbc.gridy = 6;
        spendingsGbc.gridwidth = 999;
        spendingsGbc.insets = new Insets(5, 0, 5, 0);
        content.add(spendingsLabel, spendingsGbc);

        JLabel remainderLabel = new JLabel("Залишок");
        remainderLabel.setFont(montesseratBold);
        GridBagConstraints remainderGbc = new GridBagConstraints();
        remainderGbc.gridy = 8;
        remainderGbc.gridwidth = 999;
        remainderGbc.insets = new Insets(5, 0, 5, 0);
        content.add(remainderLabel, remainderGbc);
    }

    private void addPharmacy(JPanel content, int index, ImageIcon icon, String name, double revenues, double spendings) {
        JLabel iconLabel = new JLabel(icon);
        GridBagConstraints iconGbc = new GridBagConstraints();
        iconGbc.gridx = index;
        iconGbc.gridy = 2;
        content.add(iconLabel, iconGbc);

        JLabel nameLabel = new JLabel(name);
        GridBagConstraints nameGbc = new GridBagConstraints();
        nameGbc.gridx = index;
        nameGbc.gridy = 3;
        content.add(nameLabel, nameGbc);

        JLabel revenuesLabel = new JLabel(revenues + " грн");
        GridBagConstraints revenuesGbc = new GridBagConstraints();
        revenuesGbc.gridx = index;
        revenuesGbc.gridy = 5;
        revenuesGbc.insets = new Insets(5, 15, 5, 15);
        content.add(revenuesLabel, revenuesGbc);

        JLabel spendingsLabel = new JLabel(spendings + " грн");
        GridBagConstraints spendingsGbc = new GridBagConstraints();
        spendingsGbc.gridx = index;
        spendingsGbc.gridy = 7;
        spendingsGbc.insets = new Insets(5, 15, 5, 15);
        content.add(spendingsLabel, spendingsGbc);

        JLabel remainderLabel = new JLabel(revenues - spendings + " грн");
        GridBagConstraints remainderGbc = new GridBagConstraints();
        remainderGbc.gridx = index;
        remainderGbc.gridy = 9;
        spendingsGbc.insets = new Insets(5, 15, 5, 15);
        content.add(remainderLabel, remainderGbc);
    }

    private ImageIcon loadImage(String fileName) throws IOException {
        return new ImageIcon(ImageIO.read(this.getClass().getResource("/com/resources/" + fileName)));
    }

}
