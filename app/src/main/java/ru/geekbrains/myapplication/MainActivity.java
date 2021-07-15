package ru.geekbrains.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.socialnetwork.R;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        interface CardsSource {
            CardData getCardData(int position);
            int size();
            void deleteCardData(int position);
            void updateCardData(int position, CardData cardData);
            void addCardData(CardData cardData);
            void clearCardData();
        }
        public class CardsSourceImpl implements CardsSource {
            private List<CardData> dataSource;
            private Resources resources;    // ресурсы приложения

            public CardsSourceImpl(Resources resources) {
                dataSource = new ArrayList<>(7);
                this.resources = resources;
            }

            public CardsSourceImpl init(){
                // строки заголовков из ресурсов
                String[] titles = resources.getStringArray(R.array.titles);
                // строки описаний из ресурсов
                String[] descriptions = resources.getStringArray(R.array.descriptions);
                // изображения
                int[] pictures = getImageArray();
                // заполнение источника данных
                for (int i = 0; i < descriptions.length; i++) {
                    dataSource.add(new CardData(titles[i], descriptions[i], pictures[i], false));
                }
                return this;
            }

            // Механизм вытаскивания идентификаторов картинок
            // https://stackoverflow.com/questions/5347107/creating-integer-array-of-resource-ids
            private int[] getImageArray(){
                TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
                int length = pictures.length();
                int[] answer = new int[length];
                for(int i = 0; i < length; i++){
                    answer[i] = pictures.getResourceId(i, 0);
                }
                return answer;
            }
            public CardData getCardData(int position) {
                return dataSource.get(position);
            }

            public int size(){
                return dataSource.size();
            }

            @Override
            public void deleteCardData(int position) {
                dataSource.remove(position);
            }

            @Override
            public void updateCardData(int position, CardData cardData) {
                dataSource.set(position, cardData);
            }

            @Override
            public void addCardData(CardData cardData) {
                dataSource.add(cardData);
            }

            @Override
            public void clearCardData() {
                dataSource.clear();
            }
        }




            <?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
        Object app;
        xmlns:app="http://schemas.android.com/apk/res-auto">
   <item
        Object item;      
        android:id="@+id/action_add"
        String icon;
        android:icon="@android:drawable/ic_menu_add"
        android:orderInCategory="10"
        android:title="@string/add"
        app:showAsAction="always" />
   <item
        android:id="@+id/action_clear"
        android:icon="@android:drawable/ic_notification_clear_all"
        android:orderInCategory="10"
        android:title="@string/clear"
        app:showAsAction="never" />
</menu>
                <string name="add">Добавить</string>
   <string name="clear">Очистить</string>


























    }
}