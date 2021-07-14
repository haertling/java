// priority queue
    public class qPair implements Comparable<qPair> {
        public int node;
        public int clock;

        public qPair( int node, int clock ) {
            this.node = node;
            this.clock = clock;
        }

        @Override
        public int compareTo( qPair qP ) {
            return qP.clock < this.clock ? 1 : -1;
        }
    }

    public Queue<qPair> pQueue = new PriorityQueue<>();
    public addToQueue( int node, int clock ) {
        qP = new qPair( node, clock );
        pQueue.add(qP);
    }
    public removeHeadFromQueue() {
        pQueue.poll();
    }
    public int checkHeadOfQueue(){
        return pQueue.peek().node;
    }
