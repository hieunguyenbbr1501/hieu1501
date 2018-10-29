package application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import soundpack.MaryTTS;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class Controller implements Initializable {
	Word source = new Word("data.txt");
    ArrayList<String> wordsource = source.createWordList();
	@FXML
	private TextField searchField;
	@FXML
	private ListView wordList;
	@FXML
	private Button translateButton;
	@FXML
	private TextArea translateArea;
	@FXML
	private Button refreshButton;
	@FXML
	private Button speechButton;
	public void getlist() {
		int selectedIndex = wordList.getSelectionModel().getSelectedIndex();
        searchField.setText(wordsource.get(selectedIndex));
	}
	public void refresh()
	{
		refreshButton.setOnAction(e->{
			translateArea.setText("");
			wordsource=source.createWordList();
			showlist();
		});
	}
	public void showlist()
    {

        ObservableList<String> items = FXCollections.observableArrayList(wordsource);
        wordList.setItems(items);
    }
    
    public void translate(MaryTTS tts)
    {
        translateButton.setOnAction(e->{
        	tts.setVoice("dfki-poppy-hsmm");
        	tts.speak(searchField.getText(), 2.0f, false, false);
            String meaning = source.getMeaning(searchField.getText());
            String pronounce = source.getPronounce(searchField.getText());
            translateArea.setText(pronounce+ "\n" + meaning);
        });
    }
    public void speech(MaryTTS tts)
    {
    	speechButton.setOnAction(e->{
    		tts.setVoice("dfki-poppy-hsmm");
        	tts.speak(searchField.getText(), 2.0f, false, false);
    	});
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		MaryTTS tts = new MaryTTS();
		showlist();
		refresh();
		translate(tts);
		speech(tts);
	}
}	
