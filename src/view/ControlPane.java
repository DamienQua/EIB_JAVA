package view ;

import model.* ;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.* ;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.* ;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.* ;
import javafx.stage.Stage;


public class ControlPane extends Application {
	
	Button ButNoise = new Button("NOISE") ;
	Button ButSine = new Button("SINE") ;
	Button ButSquare = new Button("SQUARE") ;
	Button ButLoad = new Button ("LOAD SIGNAL") ;
	
	ControlPane(Stage stage, Signal Sig) {
		try {
			BorderPane panel = new BorderPane() ;
			panel.setTop(createToolbar()) ;
			panel.setCenter(createMainContent(Sig)) ;
			//panel.setRight(createView(Sig));
			Scene scene = new Scene(panel, 1600, 800) ;
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()) ;
			stage.setScene(scene) ;
			stage.setTitle("Oscilloscope") ;
			stage.show() ;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Node createToolbar() {
		return new ToolBar(ButNoise, ButSine, ButSquare, new Separator(), ButLoad) ;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private Node createMainContent(Signal Sig) {
		Group g = new Group() ;
		
		ButNoise.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Sig.drawSignal(SignalType.NOISE) ;
						Screen screen = new Screen() ;
						g.getChildren().clear();
						g.getChildren().add(screen.drawScreen(Sig.getSig())) ;
						g.getChildren().add(createView(Sig)) ;
					}
			    }) ;
		
		ButSine.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Sig.drawSignal(SignalType.SINE) ;
						Screen screen = new Screen() ;
						g.getChildren().clear();
						g.getChildren().add(screen.drawScreen(Sig.getSig())) ;
						g.getChildren().add(createView(Sig)) ;
					}
			    }) ;
		
		ButSquare.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Sig.drawSignal(SignalType.SQUARE) ;
						Screen screen = new Screen() ;
						g.getChildren().clear();
						g.getChildren().add(screen.drawScreen(Sig.getSig())) ;
						g.getChildren().add(createView(Sig)) ;
					}
			    }) ;
		
		ButLoad.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
					
					}
			    }) ;
		
		
		/*Button button = new Button("Add Signal") ;
		button.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Screen.addOrRemoveSignal(true) ;
					}
			    }) ;
		
		Button button1 = new Button("Remove Signal") ;
		button1.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Screen.addOrRemoveSignal(false) ;
					}
			    }) ;*/
		
		//g.getChildren().add() ;
		
		return g ;
	}
	
	private Node createView(Signal Sig) {
		Group g = new Group() ;
		SignalTools SigT = new SignalTools() ;
		double mean = 0, variance = 0 ;
		
		mean = SigT.computeTNS(Sig, "mean") ;
		variance = SigT.computeTNS(Sig, "variance") ;
		HBox hbM = new HBox() ;
		HBox hbV = new HBox() ;
		Label labelM = new Label("Mean = ") ;
		Label labelV = new Label("Variance = ") ;
		Label labelM1 = new Label() ;
		Label labelV1 = new Label() ;
	    labelM1.setText(Double.toString(mean)) ;
	    labelV1.setText(Double.toString(variance)) ;
		hbM.getChildren().addAll(labelM, labelM1) ;
		hbM.setLayoutY(-30) ;
		hbM.setSpacing(10) ;
		g.getChildren().add(hbM) ;
		hbV.getChildren().addAll(labelV, labelV1) ;
		hbV.setLayoutY(-50) ;
		hbV.setSpacing(10) ;
		g.getChildren().add(hbV) ;
		 
		return g ;
		
	}
	

}
