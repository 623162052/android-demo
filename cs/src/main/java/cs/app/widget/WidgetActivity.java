package cs.app.widget;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import cs.app.R;
import cs.plugins.base.BaseActivity;

public class WidgetActivity extends BaseActivity {

    private static final String TAG = "WidgetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        spinner();
    }


    /**
     * Date Picker
     */
    @OnClick(R.id.btn_datepicker_open) void openDatePicker(){

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "year: " + selectedYear + " - month:" + monthOfYear + " - day:" + dayOfMonth);
            }
        }, 2016, 1, 1);
        dialog.show();
    }


    /**
     * Time Picker
     */
    @OnClick(R.id.btn_timepicker_open) void openTimePicker(){
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "hourOfDay: " + hourOfDay + " - minute: " + minute);
            }
        }, 13, 24, true);

        dialog.show();
    }

    /**
     * Spinner
     */
    @Bind(R.id.widget_spinner)
    Spinner spinner;
    public void spinner(){
        // ArrayAdapter的一种写法
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.actionbar_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setPrompt("测试");
    }

    @OnItemSelected(R.id.widget_spinner) void onSpinnerItemSelected(int position){
        String selected = (String) spinner.getItemAtPosition(position);
        Log.d(TAG, "selected item: " + selected);
    }

    /**
     * Progress Dialog
     */
    @OnClick(R.id.btn_progress_dialog)
    void openProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("ProgressDialog");
        progressDialog.setMessage("正在加载，请稍后");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);   // 进度条样式
        progressDialog.setCancelable(true);    // 屏蔽点击其他按钮
        progressDialog.show();  // 显示对话框

        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    try {
                        Thread.sleep(200);
                        // 更新进度条的进度,可以在子线程中更新进度条进度
                        progressDialog.incrementProgressBy(1);
                        // dialog.incrementSecondaryProgressBy(10)//二级进度条更新方式
                        i++;

                    } catch (Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
                // 在进度条走完时删除Dialog
                progressDialog.dismiss();

            }
        }).start();
    }

    /**
     * 文本
     */
    @OnClick(R.id.btn_alert_dialog)
    void textMsg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WidgetActivity.this);
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_info);

        builder.setMessage("确认退出吗？");
        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "关闭 onClick");
                dialog.dismiss();
                WidgetActivity.this.finish();
            }
        });

        builder.setNegativeButton("无操作", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "无操作 onClick");
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "取消 onClick");
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    /**
     * 单选
     */
    @OnClick(R.id.btn_singleChoice)
    void singleChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WidgetActivity.this);
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setSingleChoiceItems(new String[]{"1", "2", "3"}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "which: " + which);
            }
        });
        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                WidgetActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 多选
     */
    @OnClick(R.id.btn_multiChoice)
    void multiChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WidgetActivity.this);
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMultiChoiceItems(new String[]{"1", "2", "3"}, new boolean[]{true, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Log.d(TAG, "which: " + which + " - isChecked: " + isChecked);
            }
        });
        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                WidgetActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 列表
     */
    @OnClick(R.id.btn_items)
    void items() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WidgetActivity.this);
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setItems(new String[]{"Item1", "Item2"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "which: " + which);
            }
        });
        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                WidgetActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 自定义视图
     *
     * LayoutInflater 用来找res/layout/下的xml布局文件，并且实例化
     * 而findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等)
     */
    @OnClick(R.id.btn_user_defined)
    void userDefined() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.content_fragment, (ViewGroup) findViewById(R.id.content_fragment_linearLayout));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义布局").setView(layout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                WidgetActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
