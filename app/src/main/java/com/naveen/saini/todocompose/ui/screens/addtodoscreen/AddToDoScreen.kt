package com.naveen.saini.todocompose.ui.screens.addtodoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.naveen.saini.todocompose.R
import java.util.Calendar
import com.naveen.saini.todocompose.ui.componnets.TimePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoScreen(goBack: () -> Unit) {
    val viewModel = hiltViewModel<AddToDoViewModel>()
    val taskName by viewModel.taskName.collectAsState()
    val timeLabel by viewModel.time.collectAsState()

    val checked by viewModel.isToday.collectAsState()
    var showTimePicker by remember { mutableStateOf(false) }

    if (showTimePicker) {
        TimePickerDialog(
            onCancel = { showTimePicker = false },
            onConfirm = {
                viewModel.setTimeMilliseconds(it.timeInMillis)
                showTimePicker = false
            },
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(stringResource(R.string.task))
                },
                navigationIcon = {
                    IconButton(
                        onClick = { goBack() }, colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_navigation),
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 20.dp, top = 16.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.add_a_task),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.name),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 5.dp)
                )
                TextField(
                    value = taskName, onValueChange = {
                        viewModel.setTaskName(it)
                    }, singleLine = true, colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black
                    ), modifier = Modifier.fillMaxWidth()
                )
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.hour),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.clickable {
                        showTimePicker = true
                    }
                ) {
                    Text(
                        text = timeLabel,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .background(
                                Color.Gray, RoundedCornerShape(5.dp)
                            )
                            .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                            .padding(5.dp)
                    )

                }
            }

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.today),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Switch(
                    checked = checked,
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = Color.Black,
                        checkedTrackColor = Color.Black
                    ),
                    onCheckedChange = {
                        viewModel.setIsToday(it)
                    }
                )
            }

            Button(
                onClick = {
                    viewModel.addTask()
                    goBack()
                },
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(text = stringResource(R.string.done))
            }
            Text(
                text = stringResource(R.string.add_task_note),
                fontSize = 12.sp
            )

        }
    }
}