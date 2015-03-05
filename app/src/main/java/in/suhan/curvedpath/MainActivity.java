package in.suhan.curvedpath;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.suhan.curvedpath.pathanimation.AnimatorPath;
import in.suhan.curvedpath.pathanimation.PathEvaluator;
import in.suhan.curvedpath.pathanimation.PathPoint;

public class MainActivity extends ActionBarActivity {
    Button mButton;
    PathEvaluator mEvaluator = new PathEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButton = (Button) findViewById(R.id.button);

        // Set up the path we're animating along
        AnimatorPath path = new AnimatorPath();
        path.moveTo(0, 0);
        path.lineTo(0, 300);
        path.curveTo(100, 0, 300, 900, 400, 500);

        // Set up the animation
        final ObjectAnimator anim = ObjectAnimator.ofObject(this, "buttonLoc",
                new PathEvaluator(), path.getPoints().toArray());
        anim.setDuration(1000);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.start();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * We need this setter to translate between the information the animator
     * produces (a new "PathPoint" describing the current animated location)
     * and the information that the button requires (an xy location). The
     * setter will be called by the ObjectAnimator given the 'buttonLoc'
     * property string.
     */
    public void setButtonLoc(PathPoint newLoc) {
        mButton.setTranslationX(newLoc.mX);
        mButton.setTranslationY(newLoc.mY);
    }

    private ObjectAnimator zoomIn(View v, long duration, long delay){
        v.setScaleX(0);
        v.setScaleY(0);

        PropertyValuesHolder propx = PropertyValuesHolder.ofFloat("scaleX", 1);
        PropertyValuesHolder propy = PropertyValuesHolder.ofFloat("scaleY", 1);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, propx, propy);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator zoomOut(View v, long duration, long delay){
        PropertyValuesHolder propx = PropertyValuesHolder.ofFloat(View.SCALE_X, 0);
        PropertyValuesHolder propy = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, propx, propy);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;
    }
    private ObjectAnimator slideInFromTop(View v, long duration, long delay){
        v.setTranslationY(-300);
        v.setAlpha(0.5f);
        PropertyValuesHolder propA = PropertyValuesHolder.ofFloat(View.ALPHA, 1);
        PropertyValuesHolder propY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, propA, propY);
        animator.setStartDelay(delay );
        animator.setDuration(duration);
        animator.start();
        return animator;
    }
    private ObjectAnimator slideInFromBottom(View v, long duration, long delay){
        v.setTranslationY(300);
        v.setAlpha(0.5f);
        PropertyValuesHolder propA = PropertyValuesHolder.ofFloat(View.ALPHA, 1);
        PropertyValuesHolder propY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, propA, propY);
        animator.setStartDelay(delay );
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator slideInFromLeft(View v, long duration, long delay){
        v.setTranslationX(-800);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,View.TRANSLATION_X,0);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator slideInFromRight(View v, long duration, long delay){
        v.setTranslationX(800);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,View.TRANSLATION_X,0);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator slideInAndZoom(View v, long duration, long delay, float x, float y){
        v.setScaleX(0);
        v.setScaleY(0);
        v.setTranslationX(x);
        v.setTranslationY(y);

        PropertyValuesHolder propx = PropertyValuesHolder.ofFloat(View.SCALE_X, 1);
        PropertyValuesHolder propy = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1);
        PropertyValuesHolder trX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0);
        PropertyValuesHolder trY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, propx, propy, trX, trY);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator rotate(View v, long duration, long delay){
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,View.ROTATION,360);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        return animator;
    }
    private ObjectAnimator rotateAndFadeIn(View v, long duration, long delay){
        v.setAlpha(0);
        PropertyValuesHolder propA = PropertyValuesHolder.ofFloat(View.ALPHA, 1);
        PropertyValuesHolder propY = PropertyValuesHolder.ofFloat(View.ROTATION, 360);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, propA, propY);
        animator.setStartDelay(delay );
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator rotateAndZoom(View v, long duration, long delay){
        v.setScaleX(0);
        v.setScaleY(0);

        PropertyValuesHolder propx = PropertyValuesHolder.ofFloat(View.SCALE_X, 1);
        PropertyValuesHolder propy = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1);
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat(View.ROTATION, 360);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, propx, propy, rotate);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator fadeIn(View v, long duration, long delay){
        v.setAlpha(0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,View.ALPHA,1);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;

    }
    private ObjectAnimator fadeOut(View v, long duration, long delay){
        v.setAlpha(1);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,View.ALPHA,0);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.start();
        return animator;
    }
}
