package uz.devosmon.examplenavlayout

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.paypal.android.sdk.payments.*
import kotlinx.android.synthetic.main.activity_paypal.*
import org.json.JSONException
import uz.devosmon.examplenavlayout.paypal.Config
import java.math.BigDecimal


//sb-aa0yk6639558@business.example.com (US)

//Client id = "AfnQKJycZVSOMxEjIFv6M0dPeP9GigTSYZIq2LdzPqHXngEwIi-0e0PQdsFoQMg7B2JaKpmc8ayzOZGZ"

class PaypalActivity : AppCompatActivity() {

    private val deliverPerice = 25
    private var totalSum = 0
    var amount = ""
    var summa: Int = 0

    companion object {

        public val PAYPAL_REQUEST_CODE = 7171

        private val config: PayPalConfiguration = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)//use SANDBOX becos on test
            .clientId(Config.PAYPAL_CLIENT_ID)

    }

    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()

    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paypal)

        summa = intent.getIntExtra("perice", 0) as Int


        Log.d("TTTT", "${summa}")

        summaTv.text = "Products Perice: ${summa}.00$"
        deliverTv.text = "Deliver Perice: ${deliverPerice}.00$"
        totalSum = summa + deliverPerice
        totalSumma.text = "Total Perice: ${totalSum}.00$"


        //start paypal service
        val intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(intent)




        btnPayId.setOnClickListener {

            processPayment()
        }
    }

    private fun processPayment() {
        amount = totalSum.toString()

        val payPalPayment = PayPalPayment(
            BigDecimal(amount),
            "USD",
            "Donate for Delivery",
            PayPalPayment.PAYMENT_INTENT_SALE
        )

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment)
        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PAYPAL_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                val paymentConfirmation: PaymentConfirmation =
                    data!!.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (paymentConfirmation != null) {
                    try {
                        val paymentDetails = paymentConfirmation.toJSONObject().toString(4)
                        startActivity(
                            Intent(this, PaymentDetails::class.java)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount)
                        )

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cansel", Toast.LENGTH_SHORT).show()
            }
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
        }


    }

}