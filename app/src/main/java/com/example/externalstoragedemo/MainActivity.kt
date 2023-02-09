package com.example.externalstoragedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.example.externalstoragedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showExternalStorageState()
    }

    fun showExternalStorageState(){
        val removable = if(Environment.isExternalStorageRemovable()){
            "Medium kann entfernt werden"
        }else{
            "Medium kann nicht entfernt werden"
        }
        binding.tvOutput.text = removable + "\n"
        // Status abfragen
        val state =Environment.getExternalStorageState()
        val canRead:Boolean
        val canWrite:Boolean

        when(state){
            Environment.MEDIA_MOUNTED ->{
                canRead=true
                canWrite=true
            }
            Environment.MEDIA_MOUNTED_READ_ONLY ->{
                canRead=true
                canWrite=false
            }
            else ->{
                canRead=false
                canWrite=false
            }
        }
        binding.tvOutput.append(if(canRead)"Lesen ist möglich\n" else "Lesen ist nicht möglich\n")
        binding.tvOutput.append(if(canWrite)"Schreiben ist möglich\n" else "Schreiben ist nicht möglich\n")

        binding.tvOutput.append("wird emuliert? ${Environment.isExternalStorageEmulated()}")
    }
}