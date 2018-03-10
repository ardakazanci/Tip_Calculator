package ardakazanci.com.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {






    // Değeri değiştirilecek ve tıklanınca eylem başlatacak view elemanlarını butterknife kütüphanesiyle oluşturduk


    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;


    // Varsayılan değerleri oluşturuyoruz.

    float percentage = 0; // Yüzde
    float tipTotal = 0; //
    float finalBillAmount = 0; // Son Fatura Tutarı
    float totalBillAmount = 0;

    // Buttonlar tıklanınaca yüzdelik etki sabit değerleri

    float REGULAR_TIP_PERCENTAGE = 10;
    float DEFAULT_TIP_PERCENTAGE = 15;
    float EXCELLENT_TIP_PERCENTAGE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();


    }

    private void setTipValues() {

        tvTipPercent.setText(getString(R.string.main_msg_tippercent, percentage)); // %s olduğu için ikinci değeri s ' e yansıyacak
        tvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal)); // yukarıda ki ile aynı mantık
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount)); // yukarıda ki ile aynı mantık
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                // Butona tıklandığında yüzde değişecek

                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.ibGoodService:

                percentage = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_TIP_PERCENTAGE;
                break;
        }
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill(); // İlk matematiksel işlemler yapılacak
        setTipValues(); // Daha sonra buraya eklenecek

    }

    private void calculateFinalBill() {

        if (percentage == 0)
            percentage = DEFAULT_TIP_PERCENTAGE;

        if (!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount*percentage) /100;
        finalBillAmount = totalBillAmount + tipTotal; // 100 de ekleniyor. Gerekenkontroller sağlanıyor. Boş veya karakter girmediğini kontrol ediyoruz.


    }
}
