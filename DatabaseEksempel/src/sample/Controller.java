package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Connection myConn;
    private ObservableList<String> treningsøkter = FXCollections.observableArrayList();
    private ObservableList<String> øvelsegrupper = FXCollections.observableArrayList();
    private ObservableList<String> øvelseIGrupper = FXCollections.observableArrayList();



    @FXML public TextField apparatNavn;
    @FXML public TextField apparatFunksjon;
    @FXML public TextField øvelseNavn;
    @FXML public TextField treningsøktDato;
    @FXML public TextField treningsøktTid;
    @FXML public TextField treningsøktPersonligForm;
    @FXML public TextField treningsøktPrestasjon;
    @FXML public TextField treningsøktVarighet;
    @FXML public TextField treningsøktInformasjon;
    @FXML private ComboBox<String> treningsøktComboBox;
    @FXML private ComboBox<String> ØvelseComboBox;
    @FXML private ComboBox<String> øvelsesgruppeComboBox;
    @FXML private GridPane treningsøktGridPane;
    @FXML private TextField treningsøktrader;
    @FXML private TextField registrerØvelsegruppeNavn;
    @FXML private TextField registrerØvelsegruppeBeskrivelse;
    @FXML private ListView øvelserIGruppe;


    public Controller() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.myConn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/filiphag_prosjekt", "filiphag_server", "123abc");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void registrerApparat(){
        try {
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT MAX(ApparatID) FROM Apparat");
            rs2.next();
            int tallID = Integer.parseInt(rs2.getString("MAX(ApparatID)")) + 1;

            String navn = apparatNavn.getText();
            String funksjon = apparatFunksjon.getText();

            Statement myStatement = myConn.createStatement();
            String sql = "Insert into Apparat VALUES(" + tallID + ", '" + navn + "', '" + funksjon +"')";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void registrerØvelse(){
        try {
                Statement myStatement2 = myConn.createStatement();
                ResultSet rs2 = myStatement2.executeQuery("SELECT MAX(ØvelseID) FROM Øvelse");
                rs2.next();
                int øvelseID = Integer.parseInt(rs2.getString("MAX(ØvelseID)")) + 1;

                Statement myStatement3 = myConn.createStatement();
                ResultSet rs3 = myStatement3.executeQuery("SELECT * FROM Øvelsesgruppe WHERE Navn = '" + ØvelseComboBox.getValue() + "'");
                rs3.next();
                int gruppeID = Integer.parseInt(rs3.getString("ØGID"));

                String navn = øvelseNavn.getText();

                Statement myStatement4 = myConn.createStatement();
                String sql1 = "Insert into Øvelse VALUES(" + øvelseID + ", '" + navn + "')";
                System.out.println(sql1);
                myStatement4.executeUpdate(sql1);

                Statement myStatement5 = myConn.createStatement();
                String sql2 = "Insert into ØvelseIGruppe VALUES(" + øvelseID + ", " + gruppeID + ")";
                System.out.println(sql2);
                myStatement5.executeUpdate(sql2);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void registrerTreningsøkt(){
        try {
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT MAX(TøID) FROM Treningsøkt");
            rs2.next();
            int tallID = Integer.parseInt(rs2.getString("MAX(TøID)")) + 1;

            String person = treningsøktComboBox.getValue();
            Statement myStatement3 = myConn.createStatement();
            ResultSet rs3 = myStatement3.executeQuery("SELECT PID FROM Person WHERE Navn='" + person + "'");
            rs3.next();
            int PID = Integer.parseInt(rs3.getString("PID"));

            String treningsøktdato = treningsøktDato.getText();
            String treningsøkttid = treningsøktTid.getText();
            int personligform = Integer.parseInt(treningsøktPersonligForm.getText());
            int prestasjon = Integer.parseInt(treningsøktPrestasjon.getText());
            int varighet = Integer.parseInt(treningsøktVarighet.getText());
            String info = treningsøktInformasjon.getText();

            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO Treningsøkt VALUES(" + tallID + ",'" + treningsøktdato + "','" + treningsøkttid + "'," + personligform + "," + prestasjon + "," + varighet + ",'" + info + "'," + PID + ")";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void updatetreningsøktGridpane(){
        treningsøktGridPane.getChildren().retainAll(treningsøktGridPane.getChildren().get(0));
        Label Tid = new Label("Tid");
        Tid.setAlignment(Pos.CENTER);
        Tid.setMaxWidth(Double.MAX_VALUE);
        Label PersonligForm = new Label("PersonligForm");
        PersonligForm.setAlignment(Pos.CENTER);
        PersonligForm.setMaxWidth(Double.MAX_VALUE);
        Label Prestasjon = new Label("Prestasjon");
        Prestasjon.setAlignment(Pos.CENTER);
        Prestasjon.setMaxWidth(Double.MAX_VALUE);
        Label Varighet = new Label("Varighet");
        Varighet.setAlignment(Pos.CENTER);
        Varighet.setMaxWidth(Double.MAX_VALUE);
        Label Informasjon = new Label("Informasjon");
        Informasjon.setAlignment(Pos.CENTER);
        Informasjon.setMaxWidth(Double.MAX_VALUE);
        Label Notat = new Label("Notat");
        Notat.setAlignment(Pos.CENTER);
        Notat.setMaxWidth(Double.MAX_VALUE);
        treningsøktGridPane.add(Tid,0, 0);
        treningsøktGridPane.add(PersonligForm,1, 0);
        treningsøktGridPane.add(Prestasjon,2, 0);
        treningsøktGridPane.add(Varighet,3, 0);
        treningsøktGridPane.add(Informasjon,4, 0);
        treningsøktGridPane.add(Notat,5, 0);
        try {
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT * " +
                    "FROM Treningsøkt AS T JOIN Notat AS N ON (N.TøID = T.TøID)" +
                    "ORDER BY T.Dato, T.Tid ASC");
            for(int i = 1; i < Integer.parseInt(treningsøktrader.getText())+1; i++){
                rs.next();

                Label cellTid = new Label(rs.getString("Tid"));
                cellTid.setAlignment(Pos.CENTER);
                cellTid.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellTid,0, i);

                Label cellPersonligForm = new Label(rs.getString("PersonligForm"));
                cellPersonligForm.setAlignment(Pos.CENTER);
                cellPersonligForm.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellPersonligForm,1, i);

                Label cellPrestasjon = new Label(rs.getString("Prestasjon"));
                cellPrestasjon.setAlignment(Pos.CENTER);
                cellPrestasjon.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellPrestasjon,2, i);

                Label cellVarighet = new Label(rs.getString("Varighet"));
                cellVarighet.setAlignment(Pos.CENTER);
                cellVarighet.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellVarighet,3, i);

                Label cellInformasjon = new Label(rs.getString("Info"));
                cellInformasjon.setAlignment(Pos.CENTER);
                cellInformasjon.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellInformasjon,4, i);

                Label cellNotat = new Label(rs.getString("Beskrivelse"));
                cellNotat.setAlignment(Pos.CENTER);
                cellNotat.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellNotat,5, i);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void registrerØvelsesgruppe(){
        String navn = registrerØvelsegruppeNavn.getText();
        String beskrivelse = registrerØvelsegruppeBeskrivelse.getText();
        try {
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs = myStatement1.executeQuery("SELECT ØGID FROM Øvelsesgruppe");
            rs.next();
            int ØvelsesgruppeID = Integer.parseInt(rs.getString("ØGID")) + 1;

            Statement myStatement2 = myConn.createStatement();
            String sql = "INSERT INTO Øvelsesgruppe VALUES(" + ØvelsesgruppeID + ",'" + navn + "','" + beskrivelse + "')";
            System.out.println(sql);
            myStatement2.executeUpdate(sql);
        }catch(Exception e){

        }
    }

    @FXML
    public void finnØvelserIGrupper(){
        øvelserIGruppe.getItems().clear();
        øvelsegrupper.clear();
        try {
            String gruppe = øvelsesgruppeComboBox.getValue();
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs1 = myStatement1.executeQuery("SELECT ØGID FROM Øvelsesgruppe WHERE Øvelsesgruppe.Navn = '" + gruppe + "'");
            rs1.next();
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT Ø.ØvelseID, Ø.Navn " +
                    "FROM (Øvelse AS Ø JOIN ØvelseIGruppe AS ØIG ON (Ø.ØvelseID=ØIG.ØvelseID))" +
                    " JOIN Øvelsesgruppe AS ØG ON ØG.ØGID=" + rs1.getString("ØGID") +
                    "ORDER BY ØG.ØGID;");
            while(rs2.next()){
                øvelseIGrupper.add(rs2.getString("Ø.Navn"));
            }
            øvelserIGruppe.setItems(øvelseIGrupper);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            //treningsøktcombox fylles ut
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs1 = myStatement1.executeQuery("SELECT Navn FROM Person");
            while (rs1.next()) {
                String navn = rs1.getString("Navn");
                treningsøkter.add(navn);
            }
            treningsøktComboBox.setItems(treningsøkter);
            updatetreningsøktGridpane();

            //øvelsesgruppe combobox fylles ut
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT Navn FROM Øvelsesgruppe");
            while (rs2.next()) {
                String navn = rs2.getString("Navn");
                øvelsegrupper.add(navn);
            }
            øvelsesgruppeComboBox.setItems(øvelsegrupper);
            ØvelseComboBox.setItems(øvelsegrupper);
        }catch(Exception e){

        }
    }
}
