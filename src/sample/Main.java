package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.Collections;

public class Main extends Application {
    Stage window;
    TableView<Product> table;
    TextField nameinput, priceinput,quantityinput;

    Button bt;
    boolean cswitch=false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //   Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("Table");
        //name column
        TableColumn<Product, String> namecolumn = new TableColumn<>("Name");
        namecolumn.setMinWidth(200);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        namecolumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //price column
        TableColumn<Product, Double> pricecolumn = new TableColumn<>("Price");
        pricecolumn.setMinWidth(200);
        pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        pricecolumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        //quantity column
        TableColumn<Product, Integer> quantitycolumn = new TableColumn<>("Quantity");
        quantitycolumn.setMinWidth(200);
        quantitycolumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        try{
            quantitycolumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            cswitch = true;

        }catch (Exception e){
            cswitch = false;
        }



        //date column
        TableColumn<Product , LocalDate> datecolumn  = new TableColumn<>("Date");
        datecolumn.setMinWidth(200);
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        if(cswitch){
            LocalDate dt = LocalDate.now();
            datecolumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        }


        //nameinput
        nameinput = new TextField();
        nameinput.setPromptText("Name");
        priceinput = new TextField();
        priceinput.setPromptText("Price");
        quantityinput = new TextField();
        quantityinput.setPromptText("Quantity");
        ObservableList<Product> selectedProduct;

        //table.getEditingCell();
        bt = new Button("Add");
        HBox myhbox = new HBox();
        Button delete = new Button("Delete");
        delete.setOnAction(e->deleteButton());
        myhbox.getChildren().addAll(nameinput,priceinput,quantityinput,bt, delete);
        myhbox.setSpacing(10);
        myhbox.setPadding(new Insets(10,10,10,10));
        table = new TableView<>();
        table.setEditable(true);
        table.setItems(getProduct());
        table.getColumns().addAll(namecolumn,pricecolumn,quantitycolumn,datecolumn);





        //table.getSelectionModel().
        VBox myvbox = new VBox();
        myvbox.getChildren().addAll(table,myhbox);
        window.setScene(new Scene(myvbox, 800, 500));
        window.show();
    }
    public ObservableList<Product> getProduct(){
        ObservableList<Product> product = FXCollections.observableArrayList();
        product.add(new Product("Laptop",15000,6,LocalDate.of(1994,11,03)));
        product.add(new Product("Headphones",600,7,LocalDate.of(1993,11,03)));
        product.add(new Product("FlashDrive",800,9,LocalDate.of(1993,11,03)));
        bt.setOnAction(e->{
            String name = nameinput.getText();
            double price = Double.parseDouble(priceinput.getText());
            int quantity = Integer.parseInt(quantityinput.getText());
            LocalDate date = LocalDate.now();
            product.add(new Product(name,price,quantity,date));
            nameinput.clear();
            priceinput.clear();
            quantityinput.clear();
        });
        return product;
    }
    public void deleteButton(){
        ObservableList<Product> selectedProduct , allproducts;
        allproducts= table.getItems();
        selectedProduct = table.getSelectionModel().getSelectedItems();
//        for(Product pr: allproducts){
//            pr.equals(selectedProduct);
//            allproducts.remove(pr);
//        }

        selectedProduct.forEach(allproducts::remove);
//       for(int i=0;i<allproducts.size();i++){
//           if(selectedProduct==allproducts.get(i)){
//             allproducts.remove(allproducts.get(i));
//           }
//
//       }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
