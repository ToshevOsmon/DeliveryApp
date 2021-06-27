package uz.devosmon.examplenavlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_payment_details.*
import org.json.JSONException
import org.json.JSONObject

class PaymentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)


        val amount = intent

        try {
            val jsonObject = JSONObject(amount.getStringExtra("PaymentDetails"))
            showDetails(
                jsonObject.getJSONObject("response"),
                amount.getStringExtra("PaymentAmount")
            )

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun showDetails(jsonObject: JSONObject, stringExtra: String?) {

        try {
            txtId.text = jsonObject.getString("id")
            txtStatus.text = jsonObject.getString("state")
            txtAmount.text = "${stringExtra}.00$"

        } catch (e: JSONException) {
            e.printStackTrace()
        }


    }
}