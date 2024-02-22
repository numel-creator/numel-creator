package com.numel.internal

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.numel.internal.ui.theme.InternalTheme
import kotlin.math.log10
import kotlin.math.pow

private var cCO2_40: Double = 0.0
private var cCO2Vena: Double = 0.0
private var difV_AcO2: Double = 0.0
private var cont_A_O2: Double = 0.0
private var cont_V_O2: Double = 0.0
private var difA_VO2: Double = 0.0
private var cociente: Double = 0.0

var co2rangoMayor = 0.0
var co2rangoMenor = 0.0
var hiatico = 0.0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen() {
    var isColumnOneVisible by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val scroll = rememberScrollState(0)
    var txPharterial by rememberSaveable { mutableStateOf("") }
    var txCO2arterial by rememberSaveable { mutableStateOf("") }
    var txNa by rememberSaveable { mutableStateOf("") }
    var txK by rememberSaveable { mutableStateOf("") }
    var txCl by rememberSaveable { mutableStateOf("") }
    var txCa by rememberSaveable { mutableStateOf("") }
    var txHb by rememberSaveable { mutableStateOf("") }
    var txHCO3 by rememberSaveable { mutableStateOf("") }
    var txSaO2 by rememberSaveable { mutableStateOf("") }
    var txSvO2 by rememberSaveable { mutableStateOf("") }
    var txPO2arterial by rememberSaveable { mutableStateOf("") }
    var txCO2venoso by rememberSaveable { mutableStateOf("") }
    var texto by rememberSaveable { mutableStateOf("") }
    val maxLength = 6

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (isColumnOneVisible) {
            Text(text = "Pantalla 1")
            Column(
                modifier = Modifier
                    .background(Color.Cyan)
                    .weight(2F)

                    .fillMaxSize()
                    .padding(horizontal = 2.dp, vertical = 2.dp)
                        ,verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    item {

                        OutlinedTextField(
                            value = txPharterial,
                            onValueChange = {if (txPharterial.length<=maxLength){ txPharterial = it }},

                            label = {
                                Text(
                                    text = "Ph arterial",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            },


                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),


                            modifier = Modifier
                                .background(Color.DarkGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White, unfocusedTextColor = Color.LightGray, cursorColor = Color.White
                            )




                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        OutlinedTextField(
                            value = txCO2arterial,
                            onValueChange = {if (txCO2arterial.length<=maxLength){ txCO2arterial = it }},
                            label = {
                                Text(
                                    text = "CO2 arterial",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.LightGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray, cursorColor = Color.Black
                            )


                        )


                    }
                }
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    item {
                        OutlinedTextField(
                            value = txNa,
                            onValueChange = {if (txNa.length<=maxLength){ txNa = it }},
                            label = {
                                Text(
                                    text = "Na",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White, unfocusedTextColor = Color.LightGray, cursorColor = Color.White
                            )

                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        OutlinedTextField(
                            value = txK,
                            onValueChange = {if (txK.length<=maxLength){ txK = it }},
                            label = { Text(text = "K", color = Color.Black, fontSize = 12.sp) },


                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                            modifier = Modifier
                                .background(Color.LightGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray, cursorColor = Color.Black
                            )


                        )
                    }
                }
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    item {
                        OutlinedTextField(
                            value = txCl,
                            onValueChange = {if (txCl.length<=maxLength){ txCl = it }},
                            label = {
                                Text(
                                    text = "Cl",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                                    ,colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = Color.White, unfocusedTextColor = Color.LightGray, cursorColor = Color.White
                        )

                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        OutlinedTextField(
                            value = txCa,
                            onValueChange = {if (txCa.length<=maxLength){ txCa = it }},
                            label = {
                                Text(
                                    text = "Ca",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.LightGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray, cursorColor = Color.Black
                            )


                        )
                    }
                }
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    item {
                        OutlinedTextField(
                            value = txSvO2,
                            onValueChange = {if (txSvO2.length<=maxLength){ txSvO2 = it }},
                            label = {
                                Text(
                                    text = "SO2 venosa",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            },


                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                            modifier = Modifier
                                .background(Color.DarkGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White, unfocusedTextColor = Color.LightGray, cursorColor = Color.White
                            )


                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        OutlinedTextField(
                            value = txPO2arterial,
                            onValueChange = {if (txPO2arterial.length<=maxLength){ txPO2arterial = it }},
                            label = {
                                Text(
                                    text = "PO2 arterial",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.LightGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray, cursorColor = Color.Black
                            )


                        )
                    }
                }
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    item {
                        OutlinedTextField(
                            value = txCO2venoso,
                            onValueChange = {if (txCO2venoso.length<=maxLength){ txCO2venoso = it }},
                            label = {
                                Text(
                                    text = "CO2 venoso",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White, unfocusedTextColor = Color.LightGray, cursorColor = Color.White
                            )


                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        OutlinedTextField(
                            value = txHb,
                            onValueChange = {if (txHb.length<=maxLength){ txHb = it }},
                            label = {
                                Text(
                                    text = "HB",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            },


                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                            modifier = Modifier
                                .background(Color.LightGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray, cursorColor = Color.Black
                            )


                        )
                    }
                }
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    item {


                        OutlinedTextField(
                            value = txHCO3,
                            onValueChange = {if (txHCO3.length<=maxLength){ txHCO3= it }},
                            label = {
                                Text(
                                    text = "HCO3",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp)
                                .navigationBarsWithImePadding(),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White, unfocusedTextColor = Color.LightGray, cursorColor = Color.White
                            )


                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        OutlinedTextField(
                            value = txSaO2,
                            onValueChange = {if (txSaO2.length<=maxLength){ txSaO2 = it }},
                            label = {
                                Text(
                                    text = "SO2 arterial",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .background(Color.LightGray)
                                .weight(2F)
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .width(120.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            ,colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray, cursorColor = Color.Black
                            )


                        )

                    }


                }


            }
        } else {
            Text(text = "Pantalla 2")
            Column(
                modifier = Modifier
                    //.background(Color.Blue)
                    //.weight(2F)
                    .fillMaxSize()
                    //.verticalScroll(scroll)
            ) {
                Text(
                    text = texto, color = Color.Blue, fontSize = 20.sp, modifier = Modifier
                        //.fillMaxSize()
                        .weight(8F)
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .background(Color.Red)

                        //.weight(5F)
                        //.fillMaxWidth()
                        .verticalScroll(scroll)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .weight(1F)
                ) {
                    OutlinedButton(
                        onClick = {
                            isColumnOneVisible = !isColumnOneVisible

                        }, modifier = Modifier
                            .background(Color.LightGray)
                            .weight(2F)
                            .padding(horizontal = 2.dp, vertical = 2.dp)
                    ) {
                        Text(text = "Retornar", fontSize = 13.sp, color = Color.Blue)

                    }
                    OutlinedButton(
                        onClick = {
                            (context as Activity).finishAffinity()
                        }, modifier = Modifier
                            .background(Color.DarkGray)
                            .weight(2F)
                            .padding(horizontal = 2.dp, vertical = 2.dp)
                    ) {
                        Text(text = "Salir", fontSize = 13.sp, color = Color.Red)

                    }

                }


            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 2.dp)
        ) {
            Button(onClick = {
                isColumnOneVisible = !isColumnOneVisible
                if (txPharterial.isNotEmpty() && txCO2arterial.isNotEmpty() && txNa.isNotEmpty() && txK.isNotEmpty() && txCl.isNotEmpty() && txCa.isNotEmpty() && txHb.isNotEmpty() && txHCO3.isNotEmpty() && txSaO2.isNotEmpty() && txSvO2.isNotEmpty() && txPO2arterial.isNotEmpty() && txCO2venoso.isNotEmpty()) {

                    val ph1 = txPharterial.toDouble()
                    val co21 = txCO2arterial.toDouble()
                    val na1 = txNa.toDouble()
                    val k1 = txK.toDouble()
                    val cl1 = txCl.toDouble()
                    val ca1 = txCa.toDouble()
                    val hb1 = txHb.toDouble()
                    val hco31 = txHCO3.toDouble()
                    val sao21 = txSaO2.toDouble()
                    val svo21 = txSvO2.toDouble()
                    val paO21 = txPO2arterial.toDouble()
                    val cO2Venoso1 = txCO2venoso.toDouble()


                    if ((ph1 < 7.9 && ph1 > 6.7) && (co21 > 5 && co21 < 200.0) && (na1 > 90 && na1 < 200) && (k1 > 0 && k1 < 10) && (cl1 > 50 && cl1 < 160) && (ca1 > 0 && ca1 < 10)
                        && (hb1 > 0 && hb1 < 30) && (hco31 > 0 && hco31 < 60) && (sao21 > 10 && sao21 < 160) && (svo21 > 10 && svo21 < 101) && (paO21 > 10 && paO21 < 300) && cO2Venoso1 > 0
                        && ((cO2Venoso1 > co21) && (sao21 > svo21))
                    ) {


                        if ((ph1 > 7.45 || ph1 < 7.35) || (co21 > 45.0 || co21 < 35.0) || (na1 < 135 || na1 > 145) || (k1 < 3.5 || k1 > 5.5) || (ca1 < 1.1 || ca1 > 1.25) || (cl1 < 95 || cl1 > 106) || (hco31 > 27 || hco31 < 21)) {
                            //existe un trastorno acido-basico
                            texto =
                                "-> Existe un trastorno ácido-básico y/o electrolítico."


                            if (ph1 < 7.35 && co21 < 35) {
                                texto =
                                    "$texto\n->Es metabólico primario."
                                val anionGAP =
                                    (na1 + k1) - (hco31 + cl1)
                                val deita = (24 - hco31)
                                val co2esp = 40.0 - (1.2 * (deita))
                                co2rangoMayor =
                                    co2esp + (co2esp / 10.0)
                                co2rangoMenor =
                                    co2esp - (co2esp / 10.0)

                                texto =
                                    texto + "\n->Existe acidosis metabólica con:\n ->pH medido=${ph1}\n->HCO3 medido=${hco31} mmol/L. \n ->PCO2 medido=${co21} mmHg.\n->PCO2 esperada= %.2f".format(
                                        co2esp
                                    ) + " mmHg." + "\n->PCO2_Limite Mayor de Compensacion =%.2f".format(
                                        co2rangoMayor
                                    ) + " mmHg." + "\n->PCO2_Limite Menor de Compensación=%.2f".format(
                                        co2rangoMenor
                                    ) + " mmHg."


                                if (co2esp > co2rangoMayor) {
                                    texto += "\n->Acidosis respiratoria secundaria."
                                };texto = if (co2esp < co2rangoMenor) {
                                    "$texto\n->Alcalosis respiratoria secundaria."
                                } else {
                                    "$texto\n->Acidosis metabólica simple."
                                }


                                if (deita == 0.0) {


                                    texto += "\n->Anion GAP=%.2f".format(
                                        anionGAP
                                    ) + " mmol/L." + "\n->El cociente exceso de GAP/deficit de Bicarbonato no se puede calcular, puesto que el deficit de Bicarbonato es Cero." + "\n->PCO2 esperada= %.2f".format(
                                        co2esp
                                    ) + " mmHg"


                                } else {
                                    hiatico = (anionGAP - 12) / deita

                                    co2rangoMayor =
                                        co2esp + (co2esp / 10.0)
                                    co2rangoMenor =
                                        co2esp - (co2esp / 10.0)
                                    texto += "\n->Anion GAP=%.2f".format(
                                        anionGAP
                                    ) + "\n->Cociente exceso de GAP/deficit de Bicarbonato=%.2f".format(
                                        hiatico
                                    )


                                }
                                if (anionGAP > 16.0 && (hiatico < 1.0 && hiatico > 0.0)) {
                                    texto =
                                        "$texto\n->Posible coexistencia de una segunda acidosis  metabólica con Anion GAP normal acorde al cociente exceso de GAP/deficit de Bicarbonato."

                                };if (anionGAP > 16.0 && hiatico > 1.0) {
                                    texto =
                                        "$texto\n->Posible coexistencia de una alcalosis metabólica acorde al cociente exceso de GAP/deficit de Bicarbonato."

                                }


                            } else if (ph1 > 7.45 && co21 > 45.0) {
                                texto =
                                    "$texto\n->Es metabólico primario."
                                val co2esperada1 =
                                    40.0 + (0.7 * (hco31 - 24.0))
                                val co2RangoMayor =
                                    co2esperada1 + (co2esperada1 / 10)
                                val co2RangoMenor =
                                    co2esperada1 - (co2esperada1 / 10)
                                texto =
                                    texto + "\n->Hay alcalosis metabólica.\npH=${ph1}.\nPCO2=${co21} mmHg.\nHCO3=${hco31} mmol/L.\nPCO2_esperada=%.2f".format(
                                        co2esperada1
                                    ) + " mmHg." +
                                            "\n->PCO2_Limite mayor=%.2f".format(
                                                co2RangoMayor
                                            ) + " mmHg." + "\n->PCO2_Limite Menor de Compensación=%.2f".format(
                                        co2RangoMenor
                                    ) + " mmHg."

                                if (co21 > co2RangoMayor) {
                                    texto =
                                        "$texto\n->Acidosis respiratoria asociada."


                                } else if (co21 < co2RangoMenor) {
                                    texto =
                                        texto + "\n->Alcalosis respiratoria asociada.\nPCO2 esperada=%.2f".format(
                                            co2esperada1
                                        ) + " mmHg."
                                } else if (co21 in co2RangoMenor..co2RangoMayor) {
                                    texto =
                                        texto + "\n->Es una alcalosis metabólica simple.\nPCO2 esperada=%.2f".format(
                                            co2esperada1
                                        ) + " mmHg."

                                }
                            }


                            if (ph1 < 7.35 && co21 > 45) {
                                texto =
                                    "$texto->Trastorno respiratorio primario."
                                val bicar = 24 + (0.1 * (co21 - 40))
                                val hco3AgudoRangoMayor =
                                    bicar + (bicar / 10)
                                val hco3AgudoRangoMenor =
                                    bicar - (bicar / 10)
                                val hco3espCronica =
                                    24 + (0.4 * (co21 - 40))
                                val hco3CronicoRangoMayor =
                                    hco3espCronica + (hco3espCronica / 10)
                                val hco3CronicoRangoMenor =
                                    hco3espCronica - (hco3espCronica / 10)
                                texto += "\n->Hay acidosis respiratoria.\n->pH=${ph1}.\n->PCO2=${co21} mmHg.\n->HCO3=${hco31} mmol/L."
                                if (hco31 in hco3AgudoRangoMenor..hco3AgudoRangoMayor) {
                                    texto =
                                        texto + "\n->Es una acidosis respiratoria aguda simple.\n->HCO3_esperada=%.2f".format(
                                            bicar
                                        ) + " mmol/L."
                                } else if (hco31 > hco3AgudoRangoMayor) {
                                    texto =
                                        "$texto\n->Es una acidosis respiratoria aguda con alcalosis metabólica secundaria." +
                                                "\n->HCO3_Limite_agudo_mayor=%.2f".format(
                                                    hco3AgudoRangoMayor
                                                ) + " mmol/L."
                                } else if (hco31 < hco3AgudoRangoMenor) {
                                    texto =
                                        texto + "\n->Respuesta renal imcompleta en acidosis respiratoria aguda o Acidosis metabolica asociada(acidosis mixta).\nHCO3_Limite_agudo_mayor=%.2f".format(
                                            hco3AgudoRangoMenor
                                        ) + " mmol/L."

                                } else if (hco31 > hco3CronicoRangoMayor) {
                                    texto =
                                        texto + "\n->Es una acidosis respiratoria crónica con alcalosis metabólica secundaria.\n->HCO3_Limite_cronico_mayor=%.2f".format(
                                            hco3CronicoRangoMayor
                                        ) + " mmol/L."


                                } else if (hco31 < hco3CronicoRangoMenor) {
                                    texto =
                                        "$texto\n->Es una acidosis respiratoria cronica con respuesta renal incompleta  o Acidosis metabolica asociada(acidosis mixta).\n" +
                                                "->HCO3_Limite_cronico_mayor=%.2f".format(
                                                    hco3CronicoRangoMenor
                                                ) + " mmol/L."

                                }
                            } else if (ph1 > 7.45 && co21 < 35.0) {
                                texto =
                                    "$texto->Trastorno respiratorio primario."
                                val bicar = 24 + (0.2 * (40 - co21))
                                val hco3RangoAgudaMayor =
                                    bicar + (bicar / 10)
                                val hco3RangoAgudaMenor =
                                    bicar - (bicar / 10)
                                val hco3espCronica =
                                    24 + (0.4 * (40 - co21))
                                val hco3RangoCronicaMayor =
                                    hco3espCronica + (hco3espCronica / 10)
                                val hco3RangoCronicaMenor =
                                    hco3espCronica - (hco3espCronica / 10)
                                texto += "\n->Alcalosis respiratoria.\n->pH=${ph1}.\n->PCO2=${co21} mmHg\n->HCO3=${hco31} mmol/L."

                                if (hco31 > hco3RangoCronicaMayor) {
                                    texto =
                                        texto + "\n->Respuesta renal incompleta en alcalosis respiratoria crónica o Alcalosis metabolica asociada(Alcalosis mixta).\n->HCO3_esperada=%.2f".format(
                                            hco3espCronica
                                        ) + " mmHg." + "\n->HCO3_Limite_mayor_cronico=%.2f".format(
                                            hco3RangoCronicaMayor
                                        ) + " mmHg."
                                } else if (hco31 < hco3RangoCronicaMenor) {
                                    texto =
                                        texto + "\n->Alcalosis respiratoria crónica con Acidosis metabólica asociada.\n->HCO3_esperada=%.2f".format(
                                            hco3espCronica
                                        ) + " mmol/L." + "\n->HCO3_Limite_menor_cronico=%.2f".format(
                                            hco3RangoCronicaMenor
                                        ) + " mmol/L."
                                } else if (hco31 in hco3RangoCronicaMenor..hco3RangoCronicaMayor) {
                                    texto =
                                        texto + "\n->Alcalosis respiratoria crónica simple.\n->HCO3_esperada=%.2f".format(
                                            hco3espCronica
                                        ) + " mmol/L."
                                } else if (hco31 > hco3RangoAgudaMayor) {
                                    texto =
                                        texto + "\n->Respuesta renal incompleta en la alcalosis respiratoria aguda o Alcalosis metabolica asociada(Alcalosis mixta) .\n->HCO3_esperada=%".format(
                                            bicar
                                        ) + " mmol/L." +
                                                "\n->HCO3_Limite_agudo_mayor=%.2f".format(
                                                    hco3RangoAgudaMayor
                                                ) + " mmol/L."
                                } else if (hco31 < hco3RangoAgudaMenor) {
                                    texto =
                                        texto + "\n->Acidosis metabólica asociada a la alcalosis respiratoria aguda.\n->HCO3_esperada=%".format(
                                            bicar
                                        ) + " mmol/L." +
                                                "\n->HCO3_Limite_agudo_menor=%.2f".format(
                                                    hco3RangoAgudaMenor
                                                ) + " mmol/L."
                                } else if (hco31 in hco3RangoAgudaMenor..hco3RangoAgudaMayor) {
                                    texto =
                                        "$texto\n->Alcalosis respiratoria simple,HCO3 dentro rango normal." + "\n->HCO3_Limite_agudo_menor=%.2f".format(
                                            hco3RangoAgudaMenor
                                        ) + " mmol/L." +
                                                "\n->HCO3_Limite_agudo_mayor=%.2f".format(
                                                    hco3RangoAgudaMayor
                                                ) + " mmol/L"
                                }
                            } else if ((co21 in 35.0..45.0)) {
                                if (ph1 < 7.35) {
                                    texto += "Acidosis metabolica con acidosis respiratoria opuesta de igual valor,puesto que la pCO2 esta en rango normal.\n->pH=${ph1}.\n->PCO2=${co21} mmHg.\n->HCO3=${hco31} mmol/L."


                                } else if (ph1 > 7.45) {
                                    texto += "Alcalosis metabolica con Alcalosis respiratoria opuesta de igual valor,puesto que la pCO2 esta en rango normal."


                                }

                            } else if ((ph1 in 7.35..7.45)) {
                                if (co21 > 45) {

                                    texto +=
                                        "Acidosis respiratoria con alcalosis metabolica opuesta de igual valor,puesto que el pH esta en rango normal.\n->pH=${ph1}.\n->PCO2=${co21} mmHg.\n->HCO3=${hco31} mmol/L."


                                } else if (co21 < 35) {

                                    texto +=
                                        "Alcalosis respiratoria con acidosis metabolica opuesta de igual valor,puesto que el pH esta en rango normal..\n->pH=${ph1}.\n->PCO2=${co21} mmHg.\n->HCO3=${hco31} mmol/L."

                                }

                            }



                            if (txNa.toDouble() in 135.0..145.0) {
                                texto += "\n->Eunatremia. Na=${
                                    txNa.toDouble()
                                } mmol/L."
                            } else if (txNa.toDouble() > 145) {
                                texto += "\n->Existe hipernatremia. Na=${
                                    txNa.toDouble()
                                } mmol/L."
                                if (txNa.toDouble() > 145 && txNa
                                        .toDouble() <= 155
                                ) {
                                    texto += "\n->La hipernatremia es ligera. Na=${
                                        txNa.toDouble()
                                    } mmol/L."

                                } else if (txNa.toDouble() > 155 && txNa.toDouble() <= 175
                                ) {
                                    texto += "\n->La hipernatremia es severa. Na=${
                                        txNa.toDouble()
                                    } mmol/L."

                                } else if (txNa.toDouble() > 175) {
                                    texto += "\n->La hipernatremia es muy grave. Na=${
                                        txNa.toDouble()
                                    } mmol/L."


                                }

                            } else if (txNa.toDouble() < 135) {
                                texto += "\n->Existe hiponatremia. Na=${
                                    txNa.toDouble()
                                } mmol/L."

                                if (txNa.toDouble() < 135 && txNa.toDouble() >= 126
                                ) {
                                    texto += "\n->La hiponatremia es ligera. Na=${
                                        txNa.toDouble()
                                    } mmol/L."


                                } else if (txNa.toDouble() >= 111 && txNa.toDouble() < 126
                                ) {
                                    texto += "\n->La hiponatremia es severa. Na=${
                                        txNa.toDouble()
                                    } mmol/L."

                                } else if (txNa.toDouble() < 111) {
                                    texto += "\n->La hiponatremia es muy grave. Na=${
                                        txNa.toDouble()
                                    } mmol/L."

                                }

                            }; if (txK.toDouble() in 3.5..5.5) {
                                texto += "\n->Existe normocaliemia. K=${
                                    txK.toDouble()
                                } mmol/L."


                            } else if (txK.toDouble() > 5.5 && txK
                                    .toDouble() <= 7.0
                            ) {
                                texto += "\n->Hay hiperpotasemia ligera. K=${
                                    txK.toDouble()
                                } mmol/L."


                            } else if (txK.toDouble() > 7.0 && txK
                                    .toDouble() <= 8.5
                            ) {
                                texto += "\n->Hiperpotasemia grave. K=${
                                    txK.toDouble()
                                } mmol/L."


                            } else if (txK.toDouble() > 8.5) {
                                texto += "\n->Hiperpotasemia muy grave. K=${
                                    txK.toDouble()
                                } mmol/L."


                            } else if (txK.toDouble() >= 2.6 && txK
                                    .toDouble() < 3.5
                            ) {
                                texto += "\n->Hipopotasemia ligera. K=${
                                    txK.toDouble()
                                } mmol/L."

                            } else if (txK.toDouble() >= 1.6 && txK
                                    .toDouble() < 2.6
                            ) {
                                texto += "\n->Hipopotasemia grave. K=${
                                    txK.toDouble()
                                } mmol/L."


                            } else if (txK.toDouble() < 1.6) {
                                texto += "\n->Hipopotasemia muy grave. K=${
                                    txK.toDouble()
                                } mmol/L."


                            }; if (txCa.toDouble() >= (1.87 / 2) && txCa
                                    .toDouble() < (2.2 / 2)
                            ) {

                                texto += "\n->Hipocalcemia ligera. Ca=${
                                    txCa.toDouble()
                                } mEq/L."

                            } else if (txCa.toDouble() >= (1.5 / 2) && txCa
                                    .toDouble() < (1.87 / 2)
                            ) {

                                texto += "\n->Hipocalcemia grave. Ca=${
                                    txCa.toDouble()
                                } mEq/L."


                            } else if (txCa.toDouble() < (1.5 / 2)) {
                                texto += "\n->Hipocalcemia muy grave. Ca=${
                                    txCa.toDouble()
                                } mEq/L."


                            } else if (txCa.toDouble() >= (2.2 / 2) && txCa
                                    .toDouble() <= (2.5 / 2)
                            ) {

                                texto += "\n->Normocalcemia. Ca=${
                                    txCa.toDouble()
                                } mEq/L."

                            } else if (txCa.toDouble() > (2.5 / 2) && txCa
                                    .toDouble() < (3.0 / 2)
                            ) {
                                texto += "\n->Hipercalcemia ligera. Ca=${
                                    txCa.toDouble()
                                } mEq/L."

                            } else if (txCa.toDouble() in (3.0 / 2)..(3.75 / 2)) {
                                texto += "\n->Hipercalcemia grave. Ca=${
                                    txCa.toDouble()
                                } mEq/L."


                            } else if (txCa.toDouble() > (3.75 / 2)) {
                                texto += "\n->Hipercalcemia muy grave. Ca=${
                                    txCa.toDouble()
                                } mEq/L."


                            }; if (txCl.toDouble() < 95) {
                                texto += "\n->Hipocloremia. Cl=${
                                    txCl.toDouble()
                                } mmol/L."


                            } else if (txCl.toDouble() > 106) {
                                texto += "\n->Hipercloremia. Cl=${
                                    txCl.toDouble()
                                } mmol/L."


                            } else if (txCl.toDouble() in 95.0..106.0) {
                                texto += "\n->Normocloremia. Cl=${
                                    txCl.toDouble()
                                } mmol/L."


                            }
                            if (txCO2arterial.toDouble() > 45) {
                                texto += "\n->Hipercapnia. PCO2=${
                                    txCO2arterial.toDouble()
                                } mmHg."

                            } else if (txCO2arterial.toDouble() < 35) {
                                texto += "\n->Hipocapnia. PCO2=${
                                    txCO2arterial.toDouble()
                                } mmHg."

                            }; if (txHCO3.toDouble() < 21) {
                                texto += "\n->Hipobasosis. HCO3=${
                                    txHCO3.toDouble()
                                }mmol/L."

                            } else if (txHCO3.toDouble() > 27) {
                                texto += "\n->Hiperbasosis. HCO3=${
                                    txHCO3.toDouble()
                                } mmol/L."

                            }
                            val pHcal = 6.1 + log10((hco31 / (0.03 * co21)))
                            texto = texto +
                                    "\n->pH calculado=%.2f".format(pHcal) + " (ecuación Henderson-Hasselbalsh)."
                            if (paO21 > 40.0) {
                                cCO2_40 =
                                    -31.56 + (4.328 * co21) - (0.07637 * co21.pow(
                                        2.0
                                    )) + (0.000476 * co21.pow(
                                        3.0
                                    )) - ((paO21 - 40) * (1.8879 / 60))
                                cCO2Vena =
                                    -31.56 + (4.328 * cO2Venoso1) - (0.07637 * cO2Venoso1.pow(
                                        2.0
                                    )) + (0.000476 * cO2Venoso1.pow(
                                        3.0
                                    )) - ((cO2Venoso1 - 40) * (1.8879 / 60))
                                difV_AcO2 = cCO2Vena - cCO2_40
                                cont_A_O2 =
                                    (1.38 * hb1 * sao21 / 100) + 0.0031 * paO21
                                cont_V_O2 =
                                    (1.38 * hb1 * svo21 / 100) + 0.0031 * paO21//ver pvO2
                                difA_VO2 = cont_A_O2 - cont_V_O2
                                cociente = difV_AcO2 / difA_VO2
                                texto =
                                    texto + "\n->Contenido arterial estimado de CO2=%.2f".format(
                                        cCO2_40
                                    ) + " ml/dL." + "\n->Contenido venoso estimado de CO2=%.2f ".format(
                                        cCO2Vena
                                    ) + " ml/dL." + "\n->Delta c[CO2v-a]=%.2f".format(
                                        difV_AcO2
                                    ) + " ml/dL." +
                                            "\n->Contenido arterial de O2=%.2f".format(
                                                cont_A_O2
                                            ) + " ml/dL." + "\n->Contenido venoso de O2=%.2f".format(
                                        cont_V_O2
                                    ) + " ml/dL." + "\n->Diferencia A_V de O2=%.2f".format(
                                        difA_VO2
                                    ) + " ml/dL." + "\n->Delta c[CO2]/Delta c[O2]=%.2f".format(
                                        cociente
                                    ) + "."
                                if (cociente > 1.4) {
                                    texto =
                                        texto + "\n->Signo de hipoxia >> cociente (Delta c[CO2]/Delta c[O2])=%.2f".format(
                                            cociente
                                        ) + " (Mayor que 1.4)."
                                    if (svo21 < 65 && difV_AcO2 < 6) {
                                        texto += "\n->Posible:\nSaturación Baja-->Mejorar oxigenación.\nAnemia-->Transfundir.\n->Aumento del consumo de Oxigeno(\nConvulsiones,\nTremor,\nAgitación).\nSedación,antipireticos,antiepilepticos."


                                    } else if (svo21 < 65 && difV_AcO2 > 6) {
                                        texto += "\n->Gasto cardiaco bajo."


                                    } else if (svo21 > 80 && difV_AcO2 < 6) {
                                        texto += "\n->Fallo de la microcirculación y el metabolismo celular.(\n-->Eliminar sepsis\n-->Otros fallos de la microcirculación)."


                                    } else if (svo21 > 80 && difV_AcO2 > 6) {
                                        texto += "\n->Gasto cardiaco bajo.Evaluar >>(\n-->Respuesta a fluidos.\n-->Sin respuesta a fluidos,iniciar inotropicos)."


                                    }
                                } else if (cociente < 1.4) {
                                    texto += if (svo21 > 80 && difV_AcO2 < 6) {
                                        "\n->Posible Transporte de O2 en exceso. (Si resto biomarcadores normales)."


                                    } else {
                                        "\n->Si solo lactato alto,puede indicar hipoxia local o persistencia de hiperlactacidemia en recuperación de un fallo en Extraccion de O2."

                                    }
                                }


                            } else if (paO21 < 40.0) {
                                cCO2_40 =
                                    -31.56 + (4.328 * co21) - (0.07637 * co21.pow(
                                        2.0
                                    )) + (0.000476 * co21.pow(
                                        3.0
                                    )) + ((40 - paO21) * (1.8879 / 60))
                                cCO2Vena =
                                    -31.56 + (4.328 * cO2Venoso1) - (0.07637 * cO2Venoso1.pow(
                                        2.0
                                    )) + (0.000476 * cO2Venoso1.pow(
                                        3.0
                                    )) + ((40 - cO2Venoso1) * (1.8879 / 60))
                                difV_AcO2 = cCO2Vena - cCO2_40
                                cont_A_O2 =
                                    (1.38 * hb1 * sao21 / 100) + 0.0031 * paO21
                                cont_V_O2 =
                                    (1.38 * hb1 * svo21 / 100) + 0.0031 * paO21//ver pvO2
                                difA_VO2 = cont_A_O2 - cont_V_O2
                                cociente = difV_AcO2 / difA_VO2
                                texto =
                                    texto + "\n->Contenido arterial estimado de CO2=%.2f".format(
                                        cCO2_40
                                    ) + " ml/dL." + "\n->Contenido venoso de CO2=%.2f ".format(
                                        cCO2Vena
                                    ) + " ml/dL." + "\n->Delta c[CO2v-a]=%.2f".format(
                                        difV_AcO2
                                    ) + " ml/dL" +
                                            "\n->Contenido arterial de O2=%.2f".format(
                                                cont_A_O2
                                            ) + " ml/dL." + "\n Contenido venoso de O2=%.2f".format(
                                        cont_V_O2
                                    ) + " ml/dL." + "\nDiferencia A_V de O2=%.2f".format(
                                        difA_VO2
                                    ) + " ml/dL."
                                if (cociente > 1.4) {
                                    texto =
                                        texto + "\n->Signo de hipoxia >> cociente (Delta c[CO2]/Delta c[O2])=%.2f".format(
                                            cociente
                                        ) + " (Mayor que 1.4)."
                                    if (svo21 < 65 && difV_AcO2 < 6) {
                                        texto += "\n->Posible:\nSaturación Baja-->Mejorar oxigenación.\nAnemia-->Transfundir.\n->Aumento del consumo de Oxigeno(\nConvulsiones,\nTremor,\nAgitación).\nSedación,antipireticos,antiepilepticos."


                                    } else if (svo21 < 65 && difV_AcO2 > 6) {
                                        texto += "\n->Gasto cardiaco bajo."


                                    } else if (svo21 > 80 && difV_AcO2 < 6) {
                                        texto += "\n->Fallo de la microcirculación y el metabolismo celular(\n-->Eliminar sepsis\n-->Otros fallos de la microcirculación)."


                                    } else if (svo21 > 80 && difV_AcO2 > 6) {
                                        texto += "\n->Gasto cardiaco bajo.Evaluar >>(\n-->Respuesta a fluidos\n-->Sin respuesta a fluidos,iniciar inotropicos)."


                                    }
                                } else if (cociente < 1.4) {
                                    if (svo21 > 80 && difV_AcO2 < 6) {
                                        texto =
                                            texto + "\n->Posible Transporte de O2 en exceso " +
                                                    "(Si resto biomarcadores normales)."

                                    } else {
                                        texto += "\n->Si solo lactato alto,puede indicar hipoxia local o persistencia de hiperlactacidemia en recuperación de un fallo en Extraccion de O2."

                                    }
                                }


                            } else {
                                cCO2_40 =
                                    -31.56 + (4.328 * co21) - (0.07637 * co21.pow(
                                        2.0
                                    )) + (0.000476 * co21.pow(
                                        3.0
                                    ))
                                cCO2Vena =
                                    -31.56 + (4.328 * cO2Venoso1) - (0.07637 * cO2Venoso1.pow(
                                        2.0
                                    )) + (0.000476 * cO2Venoso1.pow(
                                        3.0
                                    ))
                                difV_AcO2 = cCO2Vena - cCO2_40
                                cont_A_O2 =
                                    (1.38 * hb1 * sao21 / 100) + 0.0031 * paO21
                                cont_V_O2 =
                                    (1.38 * hb1 * svo21 / 100) + 0.0031 * paO21//ver pvO2
                                difA_VO2 = cont_A_O2 - cont_V_O2
                                cociente = difV_AcO2 / difA_VO2

                                texto =
                                    texto + "\n->Contenido arterial estimado de CO2=%.2f".format(
                                        cCO2_40
                                    ) + " ml/dL." + "\n->Contenido venoso estimado de CO2=%.2f ".format(
                                        cCO2Vena
                                    ) + " ml/dL." + "\n->Delta c[CO2v-a]=%.2f".format(
                                        difV_AcO2
                                    ) + " ml/dL" +
                                            "\n->Contenido arterial de O2=%.2f".format(
                                                cont_A_O2
                                            ) + " ml/dL." + "\n->Contenido venoso de O2=%.2f".format(
                                        cont_V_O2
                                    ) + " ml/dL." + "\nDiferencia A_V de O2=%.2f".format(
                                        difA_VO2
                                    ) + " ml/dL."

                                if (cociente > 1.4) {
                                    texto =
                                        texto + "\n->Signo de hipoxia >> cociente (Delta c[CO2]/Delta c[O2])=%.2f".format(
                                            cociente
                                        ) + " (Mayor que 1.4)."
                                    if (svo21 < 65 && difV_AcO2 < 6) {
                                        texto += "\n->Posible:\nSaturación Baja-->Mejorar oxigenación\nAnemia-->Transfundir.\n->Aumento del consumo de Oxigeno(\nConvulsiones,\nTremor,\nAgitación).\nSedación,antipireticos,antiepilepticos."


                                    } else if (svo21 < 65 && difV_AcO2 > 6) {
                                        texto += "\n->Gasto cardiaco bajo."


                                    } else if (svo21 > 80 && difV_AcO2 < 6) {
                                        texto += "\n->Fallo de la microcirculación y el metabolismo celular(\n-->Eliminar sepsis\n-->Otros fallos de la microcirculación)."


                                    } else if (svo21 > 80 && difV_AcO2 > 6) {
                                        texto += "\n->Gasto cardiaco bajo.Evaluar >>(\n-->Respuesta a fluidos\n-->Sin respuesta a fluidos,iniciar inotropicos)."


                                    }
                                } else if (cociente < 1.4) {
                                    if (svo21 > 80 && difV_AcO2 < 6) {
                                        texto =
                                            texto + "\n->Posible Transporte de O2 en exceso " +
                                                    "(Si resto biomarcadores normales)."

                                    } else {
                                        texto += "\n->Si solo lactato alto,puede indicar hipoxia local o persistencia de hiperlactacidemia en recuperación de un fallo en Extraccion de O2."

                                    }
                                }

                            }

                            texto =
                                texto + "\n-> Concentración calculada [H+]=%.2f".format(
                                    10.0.pow(9 - ph1)
                                ) + " nEq/L." + "\n"



                            texto + "\n " + "     Acidosis Metabólica\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Alcalosis Metabólica\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Acidosis Respiratoria\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Alcalosis Respiratoria\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hipernatremia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hiponatremia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hiperpotasemia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hipopotasemia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hipercalcemia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hipocalcemia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hipercloremia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n" +
                                    "\n" +
                                    "     Hipocloremia\n" +
                                    "     Causas\n" +
                                    "     Clinica\n" +
                                    "     Tratamiento\n"


                        } else {

                            texto =
                                "->No existe alteración del equilibrio ácido-básico ni electrolítico."
                        }
                    } else {
                        texto = "-> Ingrese rango de valores factibles."

                    }
                } else {
                    texto = "->Es necesario llenar todos los campos."
                }
                texto += texto


                //screenResultado = true
            }) {
                Text(text = "Toggle View")
            }
            OutlinedButton(
                onClick = {
                    txPharterial = ""
                    txCO2arterial = ""
                    txNa = ""
                    txK = ""
                    txCl = ""
                    txCa = ""
                    txHb = ""
                    txHCO3 = ""
                    txSaO2 = ""
                    txSvO2 = ""
                    txPO2arterial = ""
                    txCO2venoso = ""
                    texto = ""


                }, modifier = Modifier
                    .background(Color.LightGray)
                    .weight(2F)
                    .padding(horizontal = 2.dp, vertical = 2.dp)
            ) {
                Text(text = "Borrar", fontSize = 13.sp, color = Color.Blue)

            }
            OutlinedButton(
                onClick = {
                    (context as Activity).finishAffinity()
                }, modifier = Modifier
                    .background(Color.DarkGray)
                    .weight(2F)
                    .padding(horizontal = 2.dp, vertical = 2.dp)
            ) {
                Text(text = "Salir", fontSize = 13.sp, color = Color.Red)

            }

        }
    }
}

