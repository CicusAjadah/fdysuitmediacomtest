package com.suitmedia.competencytest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.suitmedia.competencytest.databinding.ActivitySecondBinding
import com.suitmedia.competencytest.utils.NAME
import com.suitmedia.competencytest.utils.SELECTED_USER

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(NAME)

        binding.tvName.text = userName

        val activityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedUserData = data?.getStringExtra(SELECTED_USER)
                binding.tvSelectedUser.text = selectedUserData
            }
        }

        binding.btnChoose.setOnClickListener {
            val thirdIntent = Intent(this, ThirdActivity::class.java)
            activityForResult.launch(thirdIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId){
        R.id.menuBack -> {
            finish()
            true
        }
        else -> true
    }
}