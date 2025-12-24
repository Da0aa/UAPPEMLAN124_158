import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class TopUpApp extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private String selectedGame = "";
    private DefaultTableModel tableModel;
    private JTable table;

    public TopUpApp() {
        setTitle("PakDe STORE");
        setSize(700, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initHome();      // Halaman 1
        initPickGame();  // Halaman 2
        initInput();     // Halaman 3
        initHistory();   // Halaman 4

        add(mainPanel);
        setVisible(true);
    }

    private void initHome() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(20, 20, 40));
        JLabel logo = new JLabel("PakDe STORE");
        logo.setFont(new Font("Arial", Font.BOLD, 40));
        logo.setForeground(Color.CYAN);
        JButton btn = new JButton("MULAI TOP UP");
        btn.addActionListener(e -> cardLayout.show(mainPanel, "pick_game"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0; p.add(logo, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(20,0,0,0); p.add(btn, gbc);
        mainPanel.add(p, "home");
    }

    private void initPickGame() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("PILIH GAME", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel grid = new JPanel(new GridLayout(2, 2, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        String[] games = {"Mobile Legends", "Free Fire", "HOK", "AOV"};
        for (String g : games) {
            JButton btn = new JButton(g);
            btn.addActionListener(e -> { selectedGame = g; cardLayout.show(mainPanel, "input"); });
            grid.add(btn);
        }
        p.add(grid, BorderLayout.CENTER);
        p.add(new JButton("Kembali") {{ addActionListener(e -> cardLayout.show(mainPanel, "home")); }}, BorderLayout.SOUTH);
        mainPanel.add(p, "pick_game");
    }

    private void initInput() {
        JPanel p = new JPanel(new GridLayout(9, 1, 5, 5));
        p.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        JTextField txtUser = new JTextField();
        JTextField txtId = new JTextField();
        String[] pkgs = {"70 Diamonds - Rp 10000", "350 Diamonds - Rp 50000", "1500 Diamonds - Rp 200000"};
        JComboBox<String> cbPkg = new JComboBox<>(pkgs);

        JButton btnPay = new JButton("BAYAR");
        btnPay.addActionListener(e -> {
            try {
                if(txtUser.getText().isEmpty()) throw new Exception("Username Kosong!");
                if(!txtId.getText().matches("\\d+")) throw new Exception("ID Harus Angka!");

                String harga = cbPkg.getSelectedItem().toString().split("Rp ")[1];
                FileManager.save(new Transaction(txtUser.getText(), txtId.getText(), selectedGame, cbPkg.getSelectedItem().toString(), harga));
                refreshTable();
                JOptionPane.showMessageDialog(this, "Top Up Berhasil!");
                cardLayout.show(mainPanel, "history");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        });

        p.add(new JLabel("Username:")); p.add(txtUser);
        p.add(new JLabel("User ID (Angka):")); p.add(txtId);
        p.add(new JLabel("Pilih Paket:")); p.add(cbPkg);
        p.add(btnPay);
        p.add(new JButton("Kembali") {{ addActionListener(e -> cardLayout.show(mainPanel, "pick_game")); }});
        mainPanel.add(p, "input");
    }

    private void initHistory() {
        JPanel p = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"User", "ID", "Game", "Paket", "Harga"}, 0);
        table = new JTable(tableModel);

        JButton btnDel = new JButton("HAPUS RIWAYAT TERPILIH");
        btnDel.setBackground(Color.RED);
        btnDel.setForeground(Color.WHITE);
        btnDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                FileManager.delete(row);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Data dihapus!");
            } else { JOptionPane.showMessageDialog(this, "Pilih baris di tabel!"); }
        });

        p.add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel bot = new JPanel(new GridLayout(1, 2));
        bot.add(new JButton("Top Up Lagi") {{ addActionListener(e -> cardLayout.show(mainPanel, "pick_game")); }});
        bot.add(btnDel);
        p.add(bot, BorderLayout.SOUTH);
        mainPanel.add(p, "history");
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (String[] row : FileManager.load()) tableModel.addRow(row);
    }
}