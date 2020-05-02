package pub.xichen.uedbq;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SimpleAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AppActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UeActivity extends AppActivity {

    UE spineBoy;
    View spineBoyView;

    Button btnWalk, btnRun, btnJump, btnIdle, btnShoot, btnHit, btnDeath;

    class AnimData {
        String name;
        String id;

        public AnimData(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ue);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        spineBoy = new UE();
        spineBoyView = initializeForView(spineBoy, cfg);
        if (spineBoyView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) spineBoyView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }
        FrameLayout frameLayout = findViewById(R.id.container);
        frameLayout.addView(spineBoyView);

        AppCompatSpinner spinner = findViewById(R.id.spinner);
        List<AnimData> anims = new ArrayList<>();
        anims.add(new AnimData("闲置", "07_idle"));
        anims.add(new AnimData("准备", "07_standBy"));
        anims.add(new AnimData("走", "07_walk"));
        anims.add(new AnimData("跑", "07_run"));
        anims.add(new AnimData("跑（入场）", "07_run_gamestart"));
        anims.add(new AnimData("落地", "07_landing"));
        anims.add(new AnimData("攻击", "07_attack"));
        anims.add(new AnimData("攻击（扫荡）", "07_attack_skipQuest"));
        anims.add(new AnimData("庆祝-短", "07_joy_short"));
        anims.add(new AnimData("庆祝-长", "07_joy_long"));
        anims.add(new AnimData("受伤", "07_damage"));
        anims.add(new AnimData("死亡", "07_die"));
        anims.add(new AnimData("合作-准备", "07_multi_standBy"));
        anims.add(new AnimData("合作-闲置", "07_multi_idle_standBy"));
        anims.add(new AnimData("合作-闲置（无武器）", "07_multi_idle_noWeapon"));
        final List<Map<String, String>> data = new ArrayList<>();
        for (AnimData animData : anims) {
            Map<String, String> a = new HashMap<>();
            a.put("name", animData.name);
            a.put("id", animData.id);
            data.add(a);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item,
                new String[]{"name"},
                new int[]{R.id.name});
        spinner.setAdapter(simpleAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spineBoy.setAnimate(data.get(position).get("id"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addSpineBoy() {
//        final WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

//        spineBoyView.setOnTouchListener(new View.OnTouchListener() {
//
//            float lastX, lastY;
//
//            public boolean onTouch(View v, MotionEvent event) {
//                final int action = event.getAction();
//                float x = event.getRawX();
//                float y = event.getRawY();
//                if (action == MotionEvent.ACTION_DOWN) {
//                    lastX = x;
//                    lastY = y;
//                } else if (action == MotionEvent.ACTION_MOVE) {
//                    layoutParams.x += (int) (x - lastX);
//                    layoutParams.y += (int) (y - lastY);
//                    windowManager.updateViewLayout(spineBoyView, layoutParams);
//                    lastX = x;
//                    lastY = y;
//                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
//                    spineBoy.setAnimate();
//                }
//                return true;
//            }
//        });
//        layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
//        layoutParams.flags = 40;
//        layoutParams.width = dp2Px(144);
//        layoutParams.height = dp2Px(200);
//        layoutParams.format = -3;
//        windowManager.addView(spineBoyView, layoutParams);
        FrameLayout frameLayout = findViewById(R.id.container);
        frameLayout.addView(spineBoyView);
    }

    public int dp2Px(float value) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    View.OnClickListener vOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnWalk) {
                spineBoy.setAnimate("07_walk");
            } else if (view == btnRun) {
                spineBoy.setAnimate("07_run");
            } else if (view == btnJump) {
                spineBoy.setAnimate("07_landing");
            } else if (view == btnIdle) {
                spineBoy.setAnimate("07_idle");
            } else if (view == btnShoot) {
                spineBoy.setAnimate("07_attack");
            } else if (view == btnHit) {
                spineBoy.setAnimate("07_damage");
            } else if (view == btnDeath) {
                spineBoy.setAnimate("07_die");
            }
        }
    };

    @Override
    protected void onDestroy() {
        getWindowManager().removeView(spineBoyView);
        super.onDestroy();
    }
}
