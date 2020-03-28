package com.xy.testocr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.xy.ocr.at.CaptureActivity;
import com.xy.ocr.at.OcrActivity;

import exocr.exocrengine.EXOCRDict;
import exocr.exocrengine.EXOCRModel;

/**
 * Autor: Administrator
 * CreatedTime: 2020/3/28 0028
 * UpdateTime:2020/3/28 0028 9:52
 * Des:Fragment 的回调处理方式
 * UpdateContent:
 **/
public class OcrCardFragment extends Fragment {

    private ReciveOcr res;
    private TextView name;
    private TextView sex;
    private TextView nation;
    private TextView birth;
    private TextView local;
    private TextView number;
    private ImageView self;
    private TextView office;
    private TextView date;
    private ImageView country;

    public OcrCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.orc_content, container, false);
        if (getActivity() instanceof OcrActivity) {
            OcrActivity at = (OcrActivity) getActivity();
            boolean succ = EXOCRDict.InitDict(at);
            if (succ) {

            }
        }
        initView(view);
        return view;
    }

    /**
     * Autor: Administrator
     * CreatedTime: 2020/3/28 0028
     * UpdateTime:2020/3/28 0028 9:54
     * Des:视图实例化
     * UpdateContent:
     **/
    private void initView(View view) {
        view.findViewById(R.id.card_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanIntent = new Intent(getActivity(), CaptureActivity.class);
                scanIntent.putExtra(CaptureActivity.class.getSimpleName() + "front", true);
                startActivityForResult(scanIntent, 333);
//                startActivity(scanIntent);
            }
        });

        view.findViewById(R.id.card_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanIntent = new Intent(getActivity(), CaptureActivity.class);
                scanIntent.putExtra(CaptureActivity.class.getSimpleName() + "front", false);
                startActivityForResult(scanIntent, 666);
//                startActivity(scanIntent);
            }
        });
        name = (TextView) view.findViewById(R.id.card_name);
        sex = (TextView) view.findViewById(R.id.card_sex);
        nation = (TextView) view.findViewById(R.id.card_nation);
        birth = (TextView) view.findViewById(R.id.card_birth);
        local = (TextView) view.findViewById(R.id.card_local);
        number = (TextView) view.findViewById(R.id.card_number);
        self = (ImageView) view.findViewById(R.id.card_self);
        office = (TextView) view.findViewById(R.id.card_office);
        date = (TextView) view.findViewById(R.id.card_date);
        country = (ImageView) view.findViewById(R.id.card_country);
        res = new ReciveOcr();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CaptureActivity.class.getSimpleName() + CaptureActivity.class.hashCode());
        getActivity().registerReceiver(res, intentFilter);
    }

    /**
     * Autor: Administrator
     * CreatedTime: 2020/3/28 0028
     * UpdateTime:2020/3/28 0028 9:57
     * Des:Ocr广播回调
     * UpdateContent:
     **/
    private class ReciveOcr extends BroadcastReceiver {

        public ReciveOcr() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isFront = intent.getBooleanExtra(CaptureActivity.class.getSimpleName() + "isFront", false);
            EXOCRModel result = (EXOCRModel) intent.getParcelableExtra(CaptureActivity.class.getSimpleName());
            if (null == result) {
                return;
            }
            if (isFront && null != name) {
                Log.e("xy", "result = " + result.toString());

                name.setText(result.name);

                sex.setText(result.sex);

                nation.setText(result.nation);


                birth.setText(result.birth);


                local.setText(result.address);


                number.setText(result.cardnum);


                self.setImageBitmap(BitmapFactory.decodeFile(result.bitmapPath));

            } else if (null != office) {
                office.setText(result.office);
                date.setText(result.validdate);
                country.setImageBitmap(BitmapFactory.decodeFile(result.bitmapPath));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
