public void similarityDiff() {
    double maxDiff = 0.15;
    for(int i = 0; i < unmatched1.size(); i++) {
        if (file1.get(unmatched1.get(i)).trim().isEmpty())
            continue;
        int bestMatchLine = -1;
        double bestMatchScore = 10.0;

        for (int j = 0; j < unmatched2.size(); j++) {
            if(matchedLines2.contains(j) || file2.get(unmatched1.get(j)).trim().isEmpty()){
                continue;
            }

            double contentDiff = normalizedLD(file1.get(unmatched1.get(i)), file2.get(unmatched2.get(j)));

            double contextDiff = 0;
            int prevLine1 = unmatched1.get(i) - 1;
            int prevLine2 = unmatched2.get(j) - 1;
            int nextLine1 = unmatched1.get(i) + 1;
            int nextLine2 = unmatched2.get(j) + 1;
            if(unmatched1.get(i) <= 1){
                prevLine1 = unmatched1.get(i);
            }
            if(unmatched2.get(j) <= 1){
                prevLine2 = unmatched2.get(j);
            }
            if(unmatched1.get(i) >= file1_size - 1){
                nextLine1 = unmatched1.get(i);
            }
            if(unmatched2.get(j) >= file2_size - 1){
                nextLine2 = unmatched2.get(j);
            }
            contextDiff = (normalizedLD(file1.get(prevLine1), file2.get(prevLine2)) + normalizedLD(file1.get(nextLine1), file2.get(nextLine2))) / 2;

            double finalDiff = (contentDiff * 0.6) + (contextDiff * 0.4);

            if(finalDiff <= maxDiff && finalDiff < bestMatchScore){
                bestMatchScore = finalDiff;
                bestMatchLine = j;
            }
        }

        if(bestMatchLine >= 0){
            matched.add(new int[]{unmatched1.get(i), unmatched2.get(bestMatchLine)});
            matchedLines1.add(unmatched1.get(i));
            matchedLines2.add(unmatched2.get(bestMatchLine));
        }

    }
}