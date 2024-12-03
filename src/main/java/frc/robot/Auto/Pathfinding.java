package frc.robot.Auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.BooleanSupplier;

/** Pathfinding */
public class Pathfinding implements Reward{
  public enum POI {
    Note1(0.0, 0.0, 0.0, () -> ConditionBuilder.intakeHasNote()),
    Note2(0.0, 0.0, 0.0, () -> false);

    private double x_coordinates;
    private double y_coordinates;
    private Rotation2d angle;
    private Translation2d xy_coordinates;
    private BooleanSupplier conditions;

    private POI(
        double x_coordinates, double y_coordinates, double angle, BooleanSupplier removeCondition) {
      this.conditions = removeCondition;
      this.x_coordinates = x_coordinates;
      this.y_coordinates = y_coordinates;
      this.angle = new Rotation2d(angle);
      this.xy_coordinates = new Translation2d(this.x_coordinates, this.y_coordinates);
    }

    /**
     * @return the angle we want the robot to face when reaching this point
     */
    private Rotation2d getAngle() {
      return this.angle;
    }

    /**
     * @return the position of the robot when reaching the POI
     */
    private Translation2d getCoordinates() {
      return this.xy_coordinates;
    }

    private boolean getConditionStatus() {
      return this.conditions.getAsBoolean();
    }
  }

  private PathConstraints constraints =
      new PathConstraints(3.0, 4.0, Units.degreesToRadians(540), Units.degreesToRadians(720));

          @Override
public double rewardFunction() {
  // TODO put what the reward function should do
  throw new UnsupportedOperationException("Unimplemented method 'rewardFunction'");
}

      /** this function creates a list of {@link Pose2d} that can be used to make a stack
       * 
       * @param poi a list of filtered POIs that the robot must go through
       * @return a list of {@link Pose2d}
       */
  private LinkedList<Pose2d> getPointList(LinkedList<POI> poi) {
    LinkedList<Pose2d> point_list = new LinkedList<>();
    
    // make a list of coordinates
    for (POI pathPoint_poi : poi) {
      Pose2d pathPoint = new Pose2d(pathPoint_poi.getCoordinates(), pathPoint_poi.getAngle());
      point_list.add(pathPoint);
    }

    return point_list;
}
/** this methods goal is to filter through all the useful POIs and select the most advantageous one
 * 
 * @param poi a list of POIs to filter through
 * @return The {@link Pose2d} of the most useful point
 */
  private Pose2d FilterPOIs(LinkedList<POI> poi) {
    // removes POIs which conditions aren't true
    poi.removeIf(offendingPoint -> (!offendingPoint.getConditionStatus()));
    
    poi.forEach(p);
    return new Pose2d(poi.peekFirst().getCoordinates(), poi.pop().getAngle());
  }
/** executes the pathfinding command meaning that the robot should go to all chosen POIs
 * 
 * @param poi a list of POIs that the robot must go through if condtions apply
 * @return the command to pathfind to a specified point
 */
  public Command doPathfinding(LinkedList<POI> poi) {
    return Commands.run(() -> AutoBuilder.pathfindToPose(FilterPOIs(poi), constraints));
  }
}

// this class should be in constants
final class ConditionBuilder {
  // example code to demonstrate how we could use functions to make our conditions
  public static boolean intakeHasNote() {
    boolean conditionFullfilled = false;
    boolean somecondition = true;
    if (somecondition == true) {
      conditionFullfilled = true;
    }
    return conditionFullfilled;
  }
}
