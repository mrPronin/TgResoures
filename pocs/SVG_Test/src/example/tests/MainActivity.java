package example.tests;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.RectF;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(Color.WHITE);
        SVG svg = SVGParser.getSVGFromResource(getResources(), R.drawable.norway);
        imageView.setImageDrawable(svg.createPictureDrawable());
        setContentView(imageView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
