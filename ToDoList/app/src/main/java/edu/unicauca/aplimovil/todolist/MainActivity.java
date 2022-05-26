package edu.unicauca.aplimovil.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final private int REMOVE_TODO = Menu.FIRST;
    private ArrayList<String> todoItems;
    private ListView myListView;
    private EditText myEditText;
    private ArrayAdapter<String> aa;
    private boolean addingNew = false;
    private Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = (EditText) findViewById(R.id.myEditText);
        myButton = (Button) findViewById(R.id.myButton);
        myListView = (ListView) findViewById(R.id.myListView);

        todoItems = new ArrayList<String>();
        // Create the array adapter to bind the array to the listview
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        // Bind the array adapter to the listview.
        myListView.setAdapter(aa);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tareaStr = myEditText.getText().toString();
                if(tareaStr!=null && tareaStr.length()>2) {
                    todoItems.add(0,
                            tareaStr);
                    aa.notifyDataSetChanged();
                    myEditText.setText("");
                    cancelAdd();
                }
            }
        });

        registerForContextMenu(myListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(R.string.menu_title);
        menu.add(0, REMOVE_TODO, Menu.NONE, R.string.remove);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        int idx = myListView.getSelectedItemPosition();
        //If addingNew flag is set, user is adding a task so cancel option is displayed; otherwise, remove option is available
        String removeTitle = getString(addingNew ? R.string.cancel
                : R.string.remove);
        MenuItem removeItem = menu.findItem(R.id.removeItem);
        removeItem.setTitle(removeTitle);
        //Cancel option is displayed if user is adding a task and remove option is displayed if list has at least one element
        removeItem.setVisible(addingNew || idx > -1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //Get the position from the list
        int index = myListView.getSelectedItemPosition();
        switch (item.getItemId()) {
            case (R.id.removeItem): {
                //addingNew = true means the user is adding a task
                System.out.println("Eliminando un item "+index);
                if (addingNew) {
                    cancelAdd();
                } else {
                    removeItem(index);
                }
                return true;
            }
            case (R.id.addItem): {
                addNewItem();
                return true;
            }
        }
        return false;
    }

    //Handle the selection from Context Menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()) {
            case (REMOVE_TODO): {
                AdapterView.AdapterContextMenuInfo menuInfo;
                menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int index = menuInfo.position;
                removeItem(index);
                return true;
            }
        }
        return false;
    }

    private void cancelAdd() {
        addingNew = false;
        myEditText.setVisibility(View.GONE);
        myButton.setVisibility(View.GONE);
        invalidateOptionsMenu();
    }

    private void addNewItem() {
        addingNew = true;
        myEditText.setVisibility(View.VISIBLE);
        myButton.setVisibility(View.VISIBLE);
        myEditText.requestFocus();
        invalidateOptionsMenu();
    }

    private void removeItem(int _index) {
        todoItems.remove(_index);
        aa.notifyDataSetChanged();
    }

}