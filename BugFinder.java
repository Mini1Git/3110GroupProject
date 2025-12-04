import java.util.ArrayList;
public class BugFinder {
    /*
    - compare buggy (HEAD~1) vs. fixed (HEAD)
    - any line in buggy that is unmatched or modified/similarity matched was "fixed"
    - these are our target lines.
    - compare old (HEAD~2) with buggy (HEAD~1). check our target lines
    - if the target line matches something in old, the bug was already there.
    - if the target line does not match anything in old, then HEAD~1 introduced the bug
    */
}
