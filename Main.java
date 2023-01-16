package application;
	
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	
	static final String USER = "root";
	static final String PASS = "test";
	
	@Override
	public void start(Stage Stage) {
		try {
			Stage.setTitle("CoffeeDatabase Input");
			
			//labels and text fields for inputs
			Label DesLabel = new Label("Description: ");
			TextField DesInput = new TextField();
			
			Label ProdNumLabel = new Label("Product Number: ");
			TextField ProdNumInput = new TextField();
			
			Label PriceLabel = new Label("Price: ");
			TextField PriceInput = new TextField();
			
			//save button
			Button save = new Button("Save");
			
			//save event handler
			class SaveButtonHandler implements EventHandler<ActionEvent>{
				public void handle(ActionEvent event) {
					
					//save details to database
					
					//collect inputs
					String Description = DesInput.getText();
					String ProductNumber = ProdNumInput.getText();
					String Price = PriceInput.getText();
					
					
					Connection conn = null;
					Statement stmt = null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						
						System.out.println("Connecting to database...");
						conn = DriverManager.getConnection(DB_URL, USER,PASS);
						stmt = conn.createStatement();
					
						String sql = "INSERT INTO coffeedb.coffee (Description, ProdNum, Price) VALUES ('"+Description+"', '"+ProductNumber+"', '"+Price+"' )";
						stmt.executeUpdate(sql);
						
						System.out.println("Inserted Successfully");
						
						DesInput.clear();
						ProdNumInput.clear();
						PriceInput.clear();

					}catch(SQLException se) {
						se.printStackTrace();
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						try {
							if(stmt!=null)
								stmt.close();
						}catch(SQLException se2) {
							
						}
						try {
							if(conn!=null)
								conn.close();
							
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
			}
			
			}	
			//set button actions
			save.setOnAction(new SaveButtonHandler());
			
			//gridpane
			GridPane gpane = new GridPane(); 
			gpane.setHgap(15);
			gpane.setVgap(25);
			gpane.setPadding(new Insets(10));
			
			//populate grid
			gpane.add(DesLabel, 0, 0);
			gpane.add(DesInput, 1, 0);
			gpane.add(ProdNumLabel, 0, 1);
			gpane.add(ProdNumInput, 1, 1);
			gpane.add(PriceLabel, 0, 2);
			gpane.add(PriceInput, 1, 2);
			gpane.add(save, 0, 3);
			
			
			//hbox for buttons
			HBox hbox = new HBox(15,save);
			hbox.setAlignment(Pos.CENTER);
			
			//vbox
			VBox vbox = new VBox(15,gpane, hbox);
			vbox.setPadding(new Insets(10));
			
			//set scene
			Scene scene = new Scene(vbox);
			Stage.setScene(scene);
			
			Stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
