package jimmyibarra.addressbook;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Sorting extends ListActivity {

    String[] genres;
    Button sortButton;
    ArrayList<String> selectedItemsArrayList = new ArrayList<>();
    String selectedItems = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorting);

        ListView lstView = getListView();

        lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lstView.setTextFilterEnabled(true);
        genres = getResources().getStringArray(R.array.genre_array);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, genres));

        sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedItemsArrayList.contains("All")){
                    selectedItems = "All";
                }else if(selectedItemsArrayList.contains("Action") && selectedItemsArrayList.contains("Adventure") &&
                        selectedItemsArrayList.contains("Animation") && selectedItemsArrayList.contains("Comedy") &&
                        selectedItemsArrayList.contains("Family") && selectedItemsArrayList.contains("Horror") &&
                        selectedItemsArrayList.contains("Thriller")){
                    selectedItems = "All";
                }
                else {
                    for (int i = 0; i < selectedItemsArrayList.size(); i++) {
                        selectedItems += selectedItemsArrayList.get(i) + " ";
                    }
                }

                Intent intent = new Intent();
                intent.putExtra("value", selectedItems.toLowerCase());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    public void onListItemClick(ListView parent, View v, int position, long id){
        ListView lstView = getListView();
        if(lstView.isItemChecked(position)){
            selectedItemsArrayList.add(genres[position]);
        }else{
            for(int i = 0; i < selectedItemsArrayList.size(); i++){
                if(selectedItemsArrayList.get(i).equals(genres[position])){
                    selectedItemsArrayList.remove(i);
                }
            }
        }
    }
}
