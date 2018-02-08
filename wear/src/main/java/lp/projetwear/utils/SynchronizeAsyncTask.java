
package lp.projetwear.utils;

/*public class SynchronizeAsyncTask extends AsyncTask<Integer, Integer, Integer> {
    private DataClient mDataClient ;
    public SynchronizeAsyncTask(Activity mContext) {
        super();
        mDataClient = Wearable.getDataClient(mContext);
    }
        @Override
        protected Integer doInBackground(Integer... params) {
            int stepCount = params[0];
            PutDataMapRequest putDataMapReq = PutDataMapRequest.create(Constants.STEP_COUNT_PATH);
            putDataMapReq.getDataMap().putInt(Constants.STEP_COUNT_VALUE, stepCount);
            PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
            putDataReq.setUrgent();
            Task<DataItem> putDataTask = mDataClient.putDataItem(putDataReq);
            return -1;
        }
    }
*/
