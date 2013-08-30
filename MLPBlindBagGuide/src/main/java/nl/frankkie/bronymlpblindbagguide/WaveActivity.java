package nl.frankkie.bronymlpblindbagguide;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nl.fluffikens.pony.Pony;
import nl.fluffikens.pony.waves.AbstractWave;
import nl.fluffikens.pony.waves.*;


/**
 * Created by FrankkieNL on 27-8-13.
 */
public class WaveActivity extends Activity {

    AbstractWave wave;
    int waveNr = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        waveNr = getIntent().getIntExtra("waveNr", -1);
        if (waveNr == -1) {
            Toast.makeText(this, "Select a Wave", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        initWave();
        initUI();
        Log.v("MLP","Wave init done");
    }

    private void initWave() {
        switch (waveNr) {
            case 1: {
                wave = new Wave1();
                break;
            }
            case 2: {
                wave = new Wave2();
                break;
            }
            case 3: {
                wave = new Wave3();
                break;
            }
            case 4: {
                wave = new Wave4();
                break;
            }
            case 5: {
                wave = new Wave5();
                break;
            }
            case 6: {
                wave = new Wave6();
                break;
            }
            case 7: {
                wave = new Wave7();
                break;
            }
            case 8: {
                wave = new Wave8();
                break;
            } default: {
                Toast.makeText(this, "Select a Wave", Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        }
    }



    private void initUI() {
        setContentView(R.layout.activity_wave);
        //
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup container = (ViewGroup) findViewById(R.id.code_container);
        for (final Pony pony : wave.getPonies()) {
            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.layout_row_ponies, container, false);
            try {
                Drawable d = Drawable.createFromStream(getAssets().open(pony.getImageName()), null);
                ((ImageView) viewGroup.findViewById(R.id.row_icon)).setImageDrawable(d);
            } catch (Exception e) {
                //ignore
            }
            ((TextView) viewGroup.findViewById(R.id.row_title)).setText(pony.getName());
            if (pony.getDescription() == null || pony.getDescription().length == 0) {
                viewGroup.findViewById(R.id.row_secondLine).setVisibility(View.GONE);
            } else {
                StringBuilder sb = new StringBuilder();
                for (String s : pony.getDescription()) {
                    sb.append(s).append("\n");
                }
                ((TextView) viewGroup.findViewById(R.id.row_secondLine)).setText(sb.toString());
            }
            viewGroup.setFocusable(true);
            //add to container
            container.addView(viewGroup);
        }
    }

    /*
    //Temp test code

    String[] fileNames;
    private void wave2() {
        ponies.clear();
        try {
            fileNames = getAssets().list("Wave 2");
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Images of this wave could not be loaded!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        for (int i = 1; i <= 24; i++) {
            Pony p = genPony(i);
            if (p != null) {
                ponies.add(p);
            }
        }
    }

    private String[] camelCaseParser(String camelValue) {
        //http://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced
        return camelValue.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
    }


    private Pony genPony(int nr) {
        Pony pony = new Pony();
        String filePrefix = ((nr < 10) ? "0" : "") + nr;
        pony.description = new String[]{"#" + filePrefix};
        filePrefix += "_";
        String fileName = getFilename(filePrefix);
        if (fileName == null) {
            return null;
        }
        pony.imageName = "Wave " + waveNr + "/" + fileName;
        String temp = "";
        //strip number and .jpg
        String[] nameParts = camelCaseParser(fileName.substring(3, fileName.indexOf(".") ));
        for (String s : nameParts) {
            temp += s + " ";
        }
        pony.name = temp;
        return pony;
    }

    private String getFilename(String prefix) {
        for (String file : fileNames) {
            if (file.startsWith(prefix)) {
                return file;
            }
        }
        return null;
    }

    public static class Pony {

        public Pony() {
            this.name = "";
            this.imageName = "";
            this.description = new String[]{""};
        }

        public Pony(String name, String[] description, String imageName) {
            this.name = name;
            this.imageName = imageName;
            this.description = description;
        }

        String name;
        String imageName;
        String[] description;
    }
     */
}