package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import db.Koneksi;

public class KasirFrame extends JFrame{

JComboBox<String> cmbBarang;

JTextField txtHarga;
JTextField txtQty;
JTextField txtTotal;
JTextField txtBayar;
JTextField txtKembali;

JButton btnTambah;
JButton btnHapus;
JButton btnBayar;
JButton btnReset;
JButton btnStruk;

JTable table;
DefaultTableModel model;

int totalBelanja=0;


public KasirFrame(){

setTitle("Mini POS Kasir");
setSize(1000,650);
setLocationRelativeTo(null);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLayout(new BorderLayout());



JPanel header =
new JPanel();

header.setBorder(
new EmptyBorder(
20,20,20,20
)
);

JLabel title=
new JLabel(
"SISTEM KASIR DESKTOP"
);

title.setFont(
new Font(
"Arial",
Font.BOLD,
30
)
);

header.add(title);

add(
header,
BorderLayout.NORTH
);



JPanel kiri=
new JPanel();

kiri.setLayout(
new GridBagLayout()
);

kiri.setBorder(
new CompoundBorder(
new EmptyBorder(
20,20,20,20
),
new TitledBorder(
"Input Transaksi"
)
)
);

GridBagConstraints c=
new GridBagConstraints();

c.insets=
new Insets(
12,12,12,12
);

c.fill=
GridBagConstraints.HORIZONTAL;



cmbBarang=
new JComboBox<>();

txtHarga=
new JTextField();

txtQty=
new JTextField();

txtTotal=
new JTextField();

txtBayar=
new JTextField();

txtKembali=
new JTextField();


txtHarga.setEditable(false);
txtTotal.setEditable(false);
txtKembali.setEditable(false);



addField(
kiri,c,0,
"Barang",
cmbBarang
);

addField(
kiri,c,1,
"Harga",
txtHarga
);

addField(
kiri,c,2,
"Jumlah",
txtQty
);

addField(
kiri,c,3,
"Total",
txtTotal
);

addField(
kiri,c,4,
"Pembayaran",
txtBayar
);

addField(
kiri,c,5,
"Kembalian",
txtKembali
);



btnTambah=
new JButton(
"Tambah Item"
);

btnHapus=
new JButton(
"Hapus Item"
);

btnBayar=
new JButton(
"Proses Bayar"
);

btnReset=
new JButton(
"Reset"
);

btnStruk=
new JButton(
"Cetak Struk"
);


JPanel tombol=
new JPanel();

tombol.setLayout(
new GridLayout(
5,
1,
10,
10
)
);

tombol.add(
btnTambah
);

tombol.add(
btnHapus
);

tombol.add(
btnBayar
);

tombol.add(
btnReset
);

tombol.add(
btnStruk
);


c.gridx=0;
c.gridy=6;
c.gridwidth=2;

kiri.add(
tombol,
c
);

add(
kiri,
BorderLayout.WEST
);



String kolom[]={
"Barang",
"Harga",
"Qty",
"Subtotal"
};

model=
new DefaultTableModel(
kolom,
0
);

table=
new JTable(
model
);

table.setRowHeight(
30
);

table.getTableHeader()
.setFont(
new Font(
"Arial",
Font.BOLD,
14
)
);

JScrollPane sp=
new JScrollPane(
table
);

sp.setBorder(
new CompoundBorder(
new EmptyBorder(
20,20,20,20
),
new TitledBorder(
"Keranjang Belanja"
)
)
);

add(
sp,
BorderLayout.CENTER
);



loadBarang();



cmbBarang.addActionListener(
e->ambilHarga()
);

btnTambah.addActionListener(
e->tambahBarang()
);

btnHapus.addActionListener(
e->hapusItem()
);

btnBayar.addActionListener(
e->hitungBayar()
);

btnReset.addActionListener(
e->resetForm()
);

btnStruk.addActionListener(
e->cetakStruk()
);



setVisible(true);

}



private void addField(
JPanel panel,
GridBagConstraints c,
int row,
String label,
JComponent field
){

c.gridx=0;
c.gridy=row;

panel.add(
new JLabel(label),
c
);

c.gridx=1;

panel.add(
field,
c
);

}



public void loadBarang(){

try{

Connection conn=
Koneksi.getConnection();

Statement st=
conn.createStatement();

ResultSet rs=
st.executeQuery(
"SELECT nama_barang FROM barang"
);

while(
rs.next()
){

cmbBarang.addItem(
rs.getString(
"nama_barang"
)
);

}

}
catch(Exception e){
e.printStackTrace();
}

}



public void ambilHarga(){

try{

Connection conn=
Koneksi.getConnection();

PreparedStatement ps=
conn.prepareStatement(
"SELECT harga FROM barang WHERE nama_barang=?"
);

ps.setString(
1,
cmbBarang
.getSelectedItem()
.toString()
);

ResultSet rs=
ps.executeQuery();

if(
rs.next()
){

txtHarga.setText(
rs.getString(
"harga"
)
);

}

}
catch(Exception e){
e.printStackTrace();
}

}



public void tambahBarang(){

int harga=
Integer.parseInt(
txtHarga.getText()
);

int qty=
Integer.parseInt(
txtQty.getText()
);

int subtotal=
harga*qty;

model.addRow(
new Object[]{
cmbBarang
.getSelectedItem(),
harga,
qty,
subtotal
}
);

totalBelanja+=subtotal;

txtTotal.setText(
String.valueOf(
totalBelanja
)
);

txtQty.setText("");

}



public void hapusItem(){

int row=
table.getSelectedRow();

if(
row!=-1
){

int subtotal=
Integer.parseInt(
model.getValueAt(
row,
3
).toString()
);

totalBelanja-=
subtotal;

txtTotal.setText(
String.valueOf(
totalBelanja
)
);

model.removeRow(
row
);

}

}



public void hitungBayar(){

int bayar=
Integer.parseInt(
txtBayar.getText()
);

int kembali=
bayar-totalBelanja;

txtKembali.setText(
String.valueOf(
kembali
)
);

JOptionPane.showMessageDialog(
null,
"Pembayaran Berhasil"
);

}



public void resetForm(){

model.setRowCount(
0
);

totalBelanja=0;

txtTotal.setText("");
txtBayar.setText("");
txtKembali.setText("");
txtQty.setText("");

}



public void cetakStruk(){

StringBuilder struk =
new StringBuilder();

struk.append(
"============================\n"
);

struk.append(
"      TOKO ADITYA\n"
);

struk.append(
"============================\n\n"
);


for(
int i=0;
i<model.getRowCount();
i++
){

struk.append(
model.getValueAt(i,0)
);

struk.append(
" x"
);

struk.append(
model.getValueAt(i,2)
);

struk.append(
" = "
);

struk.append(
model.getValueAt(i,3)
);

struk.append(
"\n"
);

}


struk.append(
"\n----------------------------\n"
);

struk.append(
"Total     : "
+ txtTotal.getText()
+ "\n"
);

struk.append(
"Bayar     : "
+ txtBayar.getText()
+ "\n"
);

struk.append(
"Kembalian : "
+ txtKembali.getText()
+ "\n"
);

struk.append(
"\n============================\n"
);

struk.append(
" Terima Kasih\n"
);

struk.append(
"============================"
);



JTextArea area =
new JTextArea(
struk.toString()
);

area.setFont(
new Font(
"Monospaced",
Font.PLAIN,
14
)
);

area.setEditable(
false
);



int pilih=
JOptionPane.showConfirmDialog(
null,
new JScrollPane(area),
"Preview Struk - Print?",
JOptionPane.YES_NO_OPTION
);



if(
pilih==
JOptionPane.YES_OPTION
){

try{

boolean printed=
area.print();

if(
printed
){

JOptionPane.showMessageDialog(
null,
"Struk berhasil dicetak"
);

}

}
catch(Exception e){

e.printStackTrace();

}

}

}}