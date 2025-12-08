public void similarityDiff() {
    for(int i = 0; i < file1_size; i++) {
        if (file1.get(i).trim().isEmpty())
            continue;
        int bestMatchLine = -1;
        double bestMatchScore = 10.0;

        for (int j = 0; j < file2_size; j++) {
            if(matchedLines2.contains(j) || file2.get(j).trim().isEmpty()){
                continue;
            }

            double contentDiff = normalizedLD(file1.get(i), file2.get(j));

            double contextDiff = 0;

            int prevLine1 = i - 1;
            int prevLine2 = j - 1;
            int nextLine1 = i + 1;
            int nextLine2 = j + 1;
            if(i <= 1){
                prevLine1 = i;
            }
            if(j <= 1){
                prevLine2 = j;
            }
            if(i >= file1_size - 1){
                nextLine1 = i;
            }
            if(j >= file2_size - 1){
                nextLine2 = j;
            }
            contextDiff = (normalizedLD(file1.get(prevLine1), file2.get(prevLine2)) + normalizedLD(file1.get(nextLine1), file2.get(nextLine2))) / 2;

            double finalDiff = (contentDiff * 0.6) + (contextDiff * 0.4);

            if(finalDiff <= maxDiff && finalDiff < bestMatchScore){
                bestMatchScore = finalDiff;
                bestMatchLine = j;
            }
        }
        if(bestMatchLine >= 0){
            matched.add(new int[]{i, bestMatchLine});
            matchedLines1.add(i);
            matchedLines2.add(bestMatchLine);
        }

    }
}