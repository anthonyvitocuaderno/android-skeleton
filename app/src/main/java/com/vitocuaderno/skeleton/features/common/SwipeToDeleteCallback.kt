//package com.vitocuaderno.skeleton.features.common
//
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//
//class SwipeToDeleteCallback<T>: ItemTouchHelper.SimpleCallback {
//    private lateinit var mAdapter: RecyclerView.Adapter
//
//    public SwipeToDeleteCallback(adapter: RecyclerView.Adapter )
//    {
//        super(0, ItemTouchHelper.LEFT | ItemTouchHelper . RIGHT);
//        mAdapter = adapter;
//    }
//
//    override fun onMove(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        target: RecyclerView.ViewHolder
//    ): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        TODO("Not yet implemented")
//    }
//}