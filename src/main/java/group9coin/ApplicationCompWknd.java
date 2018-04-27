package group9coin;

public class ApplicationCompWknd {


    public static void bla(String... args) {
        int count = 0;
        while(count <=1) {
            nextBlock();
            count++;
        }

    }

    private static void nextBlock() {
//        RestClient restClient = new RestClient();
//        Block block = restClient.getEndBlockLongestChain();
////        System.out.println(block);
//        System.out.println("previous hash: " + block.getHash());
//        System.out.println("previous blockNr: " +block.getHeader().getBlockNumber());
////                System.out.println("target: " +block.getContent().getPointDistribution().get(0).getTarget());
////                System.out.println("points: " +block.getContent().getPointDistribution().get(0).getPoints());
//
//        Integer difficulty = restClient.getDifficulty().getValue();
//        System.out.println("Difficulty: "+ difficulty);
//        Block nextBlock = createBlock(block, difficulty);
////        System.out.println("Going to post block: "+ nextBlock);
//
//        restClient.postBlock(nextBlock);
    }

//    private static Pair<String,Header> createHashAndHeader(Block prevBlock, Content content, Integer difficulty) {
//        Integer blockNumber = prevBlock.getHeader().getBlockNumber() + 1;
//        String contentHash = Hex.encodeHexString(createContentHash(content));
//        String previousHash = prevBlock.getHash();
//        return determineHashAndHeader(blockNumber, contentHash, previousHash, difficulty);
//    }
//
//    private static Pair<String,Header> determineHashAndHeader(Integer blockNumber, String contentHash, String previousHash, Integer difficulty) {
//        Integer nonce = new Random().nextInt(Integer.MAX_VALUE);
//        Header header = new Header(blockNumber, previousHash, contentHash, String.valueOf(nonce));
//        byte[] hash = createHash(header);
//
//        while(!isValidHash(hash, difficulty)){
//            nonce += 1;
//            header = new Header(blockNumber, previousHash, contentHash, String.valueOf(nonce));
//            hash = createHash(header);
//        }
////        System.out.println("difficulty: " + difficulty);
//        String stringHash = Hex.encodeHexString(hash);
//        System.out.println("found hash string: " + stringHash);
//
//
//        return new Pair<>(stringHash, header);
//    }
//
//    private static boolean isValidHash(byte[] hash, Integer difficulty) {
//        Integer leadingZeros = leadingZeroCount(hash);
////      System.out.println("validHash? difficulty " + difficulty + " <= leadingZeros " + leadingZeros );
//        return difficulty <= leadingZeros;
//    }
//
//    private static Content createContent() {
//        List<PointDistribution> pointDistribution = new ArrayList<>();
//        PointDistribution pointDistributionEntry = new PointDistribution("TAP-JD",100);
//        pointDistribution.add(pointDistributionEntry);
//        return new Content(pointDistribution);
//    }
//
//    private static Block createBlock(Block prevBlock, Integer difficulty) {
//        Content content = createContent();
//        Pair<String,Header> hashAndHeader = createHashAndHeader(prevBlock, content, difficulty);
//        return new Block(hashAndHeader.getKey(),  hashAndHeader.getValue(), content);
//
//    }
//
//
//    private static byte[] createContentHash(Content content) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "";
//
//        try {
//            json = objectMapper.writeValueAsString(content);
//        }catch(JsonProcessingException e){
//            System.out.println("Failure");
//
//        }
////        System.out.println(json);
//
//        return createHash(json);
//    }
//
//    private static byte[] createHash(Header header) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "";
//
//        try {
//            json = objectMapper.writeValueAsString(header);
//        }catch(JsonProcessingException e){
//            System.out.println("Failure");
//
//        }
////        System.out.println(json);
//
//        return createHash(json);
//    }
//
//    private static byte[] createHash(String stringToHash) {
//        return DigestUtils.sha256(stringToHash);
//    }
//
//
//    private static int leadingZeroCount(byte[] hash) {
//        int totalCountLeadingZeros = 0;
//        byte[] var4 = hash;
//        int var5 = hash.length;
//
//        for(int var6 = 0; var6 < var5; ++var6) {
//            byte b = var4[var6];
//            int lzc = leadingZeroCountOfByte(b);
//            totalCountLeadingZeros += lzc;
//            if (lzc != 8) {
//                break;
//            }
//        }
//
//        return totalCountLeadingZeros;
//    }
//
//    private static int leadingZeroCountOfByte(byte b) {
//        int i = b & 255;
//        return Integer.numberOfLeadingZeros(i) - 24;
//    }
//
//
//

}