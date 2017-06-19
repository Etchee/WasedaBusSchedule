package etchee.com.wasedabusschedule.Data

/**
 * Data model to summarize bus schedule
 * Created by rikutoechigoya on 2017/06/19.
 */
class DataList {

    fun createWasedaData():ArrayList<DataModel>{
        val arrayList = arrayListOf<DataModel>()
        arrayList.add(DataModel("09", "20", 0, 920))
        arrayList.add(DataModel("09", "35", 0, 935))
        arrayList.add(DataModel("09", "50", 0, 950))
        arrayList.add(DataModel("10", "05", 0, 1005))
        arrayList.add(DataModel("10", "35", 0, 1035))
        arrayList.add(DataModel("10", "45", 0, 1045))
        arrayList.add(DataModel("11", "00", 0, 1100))
        arrayList.add(DataModel("11", "30", 0, 1130))
        arrayList.add(DataModel("12", "05", 4, 1205))
        arrayList.add(DataModel("12", "20", 5, 1220))
        arrayList.add(DataModel("12", "45", 0, 1245))
        arrayList.add(DataModel("13", "00", 0, 1300))
        arrayList.add(DataModel("13", "30", 0, 1330))
        arrayList.add(DataModel("14", "05", 0, 1405))
        arrayList.add(DataModel("14", "35", 6, 1435))
        arrayList.add(DataModel("14", "50", 0, 1450))
        arrayList.add(DataModel("15", "05", 0, 1505))
        arrayList.add(DataModel("15", "20", 0, 1520))
        arrayList.add(DataModel("15", "35", 0, 1535))
        arrayList.add(DataModel("15", "50", 0, 1550))
        arrayList.add(DataModel("16", "20", 6, 1620))
        arrayList.add(DataModel("16", "35", 0, 1635))
        arrayList.add(DataModel("17", "00", 0, 1700))
        arrayList.add(DataModel("17", "20", 0, 1720))
        arrayList.add(DataModel("18", "10", 0, 1810))
        arrayList.add(DataModel("18", "25", 8, 1825))

        return arrayList
    }

     fun createSat_WasedaData():ArrayList<DataModel>{
        val arrayList = arrayListOf<DataModel>()
        arrayList.add(DataModel("09", "35", 0, 935))
        arrayList.add(DataModel("10", "35", 0, 1035))
        arrayList.add(DataModel("11", "05", 0, 1105))
        arrayList.add(DataModel("11", "45", 0, 1145))
        arrayList.add(DataModel("12", "15", 0, 1215))
        arrayList.add(DataModel("12", "45", 0, 1245))
        arrayList.add(DataModel("14", "00", 0, 1400))
        arrayList.add(DataModel("14", "35", 0, 1435))
        arrayList.add(DataModel("15", "20", 0, 1520))
        arrayList.add(DataModel("16", "20", 0, 1620))
        return arrayList
    }


     fun createNishiData():ArrayList<DataModel>{
        val arrayList = arrayListOf<DataModel>()
        arrayList.add(DataModel("09", "20", 0, 920))
        arrayList.add(DataModel("09", "35", 0, 935))
        arrayList.add(DataModel("09", "50", 0, 950))
        arrayList.add(DataModel("10", "05", 0, 1005))
        arrayList.add(DataModel("10", "35", 0, 1035))
        arrayList.add(DataModel("10", "45", 0, 1045))
        arrayList.add(DataModel("11", "15", 0, 1115))
        arrayList.add(DataModel("11", "45", 0, 1145))
        arrayList.add(DataModel("12", "05", 1, 1205))
        arrayList.add(DataModel("12", "15", 2, 1215))
        arrayList.add(DataModel("12", "45", 0, 1245))
        arrayList.add(DataModel("13", "15", 0, 1315))
        arrayList.add(DataModel("13", "45", 0, 1345))
        arrayList.add(DataModel("14", "05", 0, 1405))
        arrayList.add(DataModel("14", "35", 0, 1435))
        arrayList.add(DataModel("14", "50", 3, 1450))
        arrayList.add(DataModel("15", "05", 0, 1505))
        arrayList.add(DataModel("15", "20", 0, 1520))
        arrayList.add(DataModel("15", "35", 0, 1535))
        arrayList.add(DataModel("15", "50", 0, 1550))
        arrayList.add(DataModel("16", "20", 0, 1620))
        arrayList.add(DataModel("16", "35", 3, 1635))
        arrayList.add(DataModel("17", "00", 0, 1700))
        arrayList.add(DataModel("17", "20", 0, 1720))
        arrayList.add(DataModel("18", "10", 3, 1810))
        return arrayList
    }


     fun createSat_NishiData():ArrayList<DataModel>{
        val arrayList = arrayListOf<DataModel>()
        arrayList.add(DataModel("09", "20", 0, 920))
        arrayList.add(DataModel("10", "20", 0, 1020))
        arrayList.add(DataModel("10", "50", 0, 1050))
        arrayList.add(DataModel("11", "30", 0, 1130))
        arrayList.add(DataModel("12", "00", 0, 1200))
        arrayList.add(DataModel("12", "30", 0, 1230))
        arrayList.add(DataModel("13", "45", 0, 1345))
        arrayList.add(DataModel("14", "15", 0, 1415))
        arrayList.add(DataModel("14", "50", 0, 1450))
        arrayList.add(DataModel("15", "50", 0, 1550))
        arrayList.add(DataModel("16", "35", 0, 1635))
        return arrayList
    }


    data class DataModel(val hour:String,
                         val min:String,
                         val flag:Int,
                         val search:Int )
}
