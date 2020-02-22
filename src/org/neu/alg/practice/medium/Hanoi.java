package org.neu.alg.practice.medium;

public class Hanoi {

  enum Rod {
    Source,
    Mediator,
    Destination,
  }

  Hanoi(int diskCount) {
    this.log(String.format("## [INFO] Start to tackle %d disks over 3 Hanoi rods", diskCount));
    this.solve(diskCount, Rod.Source, Rod.Mediator, Rod.Destination);
    this.log(String.format("\n[INFO] ---------------END-%d----------------\n", diskCount));

  }

  /**
   * Recursion logic
   * - Assume we have three rods, namely Source, Destination and Mediator
   * - Simplify the problem to 1 disk, the recursion handler
   * <p>
   * - Case: 1 dick in Source
   * - move disk 1 from Source to Destination
   * <p>
   * - Case: 2 disks in Source
   * - move disk 1 from Source to Mediator
   * - move disk 2 from Source to Destination
   * <p>
   * - move disk 1 from Mediator to Destination
   * <p>
   * // The last 1 step is indeed a transformed Case 1 issue,
   * where we switch places for rod Source and rod Mediator
   * <p>
   * - Case: 3 disks in Source
   * - move disk 1 from Source to Destination
   * - move disk 2 from Source to Mediator
   * - move disk 1 from Destination to Mediator
   * <p>
   * // The upon 3 steps is indeed a transformed Case 2 issue,
   * where we switch places for rod Mediator and rod Destination
   * <p>
   * - move disk 3 from Source to Destination
   * <p>
   * - move disk 1 from Mediator to Source
   * - move disk 2 from Mediator to Destination
   * - move disk 1 from Source to Destination
   * <p>
   * // The upon 3 steps is indeed a transformed Case 2 issue too,
   * where we switch places for rod Source and rod Mediator
   *
   * @param currentDiskIndex
   */
  private void solve(int currentDiskIndex, Rod fromRodName, Rod unusedRodName, Rod toRodName) {

    if (currentDiskIndex == 1) {
      this.log(String.format("* Move disk %d from %s to %s", currentDiskIndex, fromRodName, toRodName));
      return;
    }

    // the `currentDiskIndex` problem could be solved by

    this.solve(currentDiskIndex - 1, fromRodName, toRodName, unusedRodName);

    this.log(String.format("* Move disk %d from %s to %s", currentDiskIndex, fromRodName, toRodName));

    this.solve(currentDiskIndex - 1, unusedRodName, fromRodName, toRodName);

  }

  private void log(String msg) {
    System.out.println(msg);
  }

  public static void main(String[] arg) {

    new Hanoi(1);
    new Hanoi(2);
    new Hanoi(3);
    new Hanoi(4);

  }
}
