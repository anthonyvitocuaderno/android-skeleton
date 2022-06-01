package com.vitocuaderno.skeleton.features.main.calendar.week

import android.content.Context
import android.os.Bundle
import android.view.View
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.databinding.FragmentWeekCalendarBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeekCalendarFragment : BaseFragment<FragmentWeekCalendarBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_week_calendar

    override val title = "Week"

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SAMPLE
        val meetingBlocks: MutableList<View> = mutableListOf()

        meetingBlocks.add(createMeetingBlock(requireContext()))

        // clear meetingBlocks
    }

    private fun createMeetingBlock(context: Context): View { // (meeting: Meeting) {
        // determine the position

        // 0 - 6 or sunday to saturday ?? may differ. 2x check
        val day = 3 // DateUtils.getCalendarDay(meeting.dateStart)
        // DateUtils.getTimeRoundedToNearestHalfHour(meeting.dateStart)
        val time = "12:00" // 0-24H format is easier to process
        // DateUtils.getDurationInMinutes(meeting.dateStart, meeting.dateEnd) / 30
        val durationInHalfHours = 3; // 3 * 30min or 1.5hours

        // getWeekColumn(day = Int): ViewGroup. based on variable "day"
        val column = binding.columnWed
        // getTimeSlot(column, time) based on variable "time"
        val referenceBlock = column.t12pm

        // inflate res.layout.block_week_calendar
        // add constraints
        // attach to column
        return View(context)
    }
}
