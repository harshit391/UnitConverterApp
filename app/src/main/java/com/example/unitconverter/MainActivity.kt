package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember {
        mutableStateOf("")
    }

    var outputValue by remember{
        mutableStateOf("")
    }

    var inputUnit by remember {
        mutableStateOf("Select")
    }

    var outputUnit by remember {
        mutableStateOf("Select")
    }

    var iExpanded by remember{
        mutableStateOf(false)
    }

    var oExpanded by remember {
        mutableStateOf(false)
    }

    var conversionFactor by remember {
        mutableDoubleStateOf(1.00)
    }

    var oConversionFactor by remember {
        mutableDoubleStateOf(1.00)
    }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace, // Replace with your desired font family
        fontSize = 42.sp, // Replace with your desired font size
        color = Color.Yellow // Replace with your desired text color
    )

    fun convertUnit() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble/ conversionFactor) * oConversionFactor
        print(result)
        outputValue = result.toString()
    }

    fun outputCorrect() {
        var end:Int = outputValue.length-1
        var present = false
        while(end >= 0 && outputValue[end]!='.') {
            if (outputValue[end]=='E') {
                present = true
                break
            }
            end--
        }
        outputValue = if (present) {
            val toAdd = outputValue.substring(end, outputValue.length)
            outputValue.substring(0, minOf(end, 6)) + toAdd
        } else {
            outputValue.substring(0, minOf(outputValue.length, 6))
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter" /*, modifier =  Modifier.padding(100.dp) */ , style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
        /* Here Goes What should happen when the value of our OutlinedTextField Changes*/
            inputValue = it
            convertUnit()
        }, label = { Text("Enter Value") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Green,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        /* Here all he UI elements will be stacked below each other*/
        Row {
            Box{
                Button(onClick = { iExpanded = true },shape = RectangleShape, colors = ButtonDefaults.buttonColors
                    (Color.White), border = BorderStroke(2.dp, Color.Blue)) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down Arrow")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor = 100.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilometer")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Kilometer"
                            conversionFactor = 0.001
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor = 1000.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Micrometer")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Micrometer"
                            conversionFactor = 1000000.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Nanometer")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Nanometer"
                            conversionFactor = 1000000000.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Mile")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Mile"
                            conversionFactor = 0.0006213689
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Yard")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Yard"
                            conversionFactor = 1.0936132983
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor = 3.280839895
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inch")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Inch"
                            conversionFactor = 39.37007874
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { oExpanded = true },shape = RectangleShape, colors = ButtonDefaults.buttonColors
                    (Color.White), border = BorderStroke(2.dp, Color.Blue)
                ) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down Arrow")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor = 100.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilometer")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Kilometer"
                            oConversionFactor = 0.001
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor = 1000.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Micrometer")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Micrometer"
                            oConversionFactor = 1000000.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Nanometer")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Nanometer"
                            oConversionFactor = 1000000000.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Mile")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Mile"
                            oConversionFactor = 0.0006213689
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Yard")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Yard"
                            oConversionFactor = 1.0936132983
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor = 3.280839895
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inch")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Inch"
                            oConversionFactor = 39.37007874
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (outputUnit != "Select" && inputUnit!="Select" && inputValue.isNotEmpty() && outputValue.isNotEmpty()) {
            outputCorrect()
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .border(2.dp, Color.Yellow, RectangleShape)
            ){
                Text("Result",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = Color(0xFF87CEEB),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    "$outputValue $outputUnit",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    color = Color.Green
                )
            }

        }
    }
}

