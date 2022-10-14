import javax.swing.*;
import java.util.Scanner;

/** DatabaseTester is a test class that ensures
 *  database.java and its objects function properly
 *
 * @author drgazelle
 * @version V1.0
 */
public class DatabaseTester {
    public static void main(String[] args) {
        //terminal scanner
        Scanner input = new Scanner(System.in);
        // creates database class and imports data.txt
        Database data = new Database();
        //wipes old database
        data.clear();

        data.add(new Course("Course"));
        data.add(new Course("Geometry"));
        data.add(new Course("Biology"));

        //Adds decks and flashcards to Algebra
        data.get(0).add(new Deck("Deck 1", "Description 1"));
        data.get(0).get(0).add(new Flashcard("Term", "Def"));
        data.get(0).get(0).add(new Flashcard("Term", "Def"));

        data.get(0).add(new Deck("Deck 2", "Description 2"));
        data.get(0).get(1).add(new Flashcard("Term", "Def"));
        data.get(0).get(1).add(new Flashcard("Term", "Def"));

        //Adds decks and flashcards to Geometry
        data.get(1).add(new Deck("Unit 1"));
        data.get(1).get(0).importQuizlet("Postulate/Axiom\tAn accepted statement of fact####Point\tindicates a location and has no size. Named with a capital letter.####Line\tA straight path extends in opposite ends and has no thickness. Contains infinitely many points.####Plane\tA flat surface that extends without end and has no thickness. Contains infinitely many lines.####Col-linear points\tPoints that lie on the same line####co-planar points\tpoints that lie on the same plane####line segment\tA part of a line between two endpoints and all points between them.####Ray\tA part of a line, with one endpoint, that continues without end in one direction and all points on one side of the end point.####Opposite Rays\tTwo rays that share the same endpoint and form a line####Postulate 1-1\tThrough any two points there is exactly one line####Postulate 1-2\tIf two distinct lines intersect, then they intersect in exactly one point.####Postulate 1-3\tIf two distinct planes intersect, then they intersect in exactly one line.####Postulate 1-4\tThrough any three noncollinear points there is exactly one plane####Coordinate\tThe real number that corresponds to a point####Postulate 1-5\tEvery point on a line can be paired with a real number####Postulate 1-6\tIf three points A, B, and C are collinear and B is between A and C, then AB+BC=AC####Distance Formula\td = √[( x₂ - x₁)² + (y₂ - y₁)²]####Midpoint formula\t(x₁+x₂)/2, (y₁+y₂)/2####Midpoint\tA point that divides a segment into two congruent segments####Bisect\tTo divide into two congruent parts####Bisector\tA point, line or line segment that divides a segment or angle into two equal parts.####Perpendicular bisector\tA segment, ray, line, or plane that is perpendicular to a segment at its midpoint####Angle bisector\ta ray that divides an angle into two congruent angles####Angle\tformed by two rays with the same endpoint####Sides of an angle\tThe rays of an angle####Vertex of the angle\tThe initial points of the rays####Interior of an angle\tThe region that contains all the points between the sides of an angle####Exterior of an angle\tThe region that contains all the points outside of an angle####Right angle\tAn angle that measures 90 degrees####Acute angle\tAn angle that is less than 90 degrees####Obtuse angle\tAn angle that measures more than 90 degrees but less than 180 degrees####Straight angle\tAn angle that measures exactly 180 degrees####Reflex angle\tBetween 180 and 360 degrees####Protractor Postulate\tGiven any angle, the measure can be put into one-to-one correspondence with real numbers between 0 and 180.####Congruent angles\tAngles that have the same measure####Angle Addition Postulate\tIf P is in the interior of <RST, then m<RSP + m<PST = m<RST####Adjacent Angles\ttwo angles that share a common vertex and side, but have no common interior points####Linear Pair\tA pair of adjacent angles whose non-common sides are opposite rays. The angles form a straight angle####Complementary Angles\tTwo angles that add up to 90 degrees. Each angle is called the compliment of the other.####Supplementary Angles\tTwo angles whose sum is 180 degrees. Each angle is called the supplement of the other.####Postulate 1-9\tIf two angles form a linear pair, then they are supplementary.####Inductive Reasoning\tA type of logic in which generalizations are based on a large number of specific observations.####Conjecture\tan opinion or conclusion formed on the basis of incomplete information####Counterexample\tan example used to support a claim or statement that is the opposite of another claim or statement####Conditional Statement\tA statement that can be written in if-then form. If p, then q.####Hypothesis\tA testable prediction, often implied by a theory####Conclusion\tA summary based on evidence or facts####Truth Value\tthe truth or falsity of a statement (T or F)####Converse\tIf q, then p (if conclusion, then hypothesis)####Inverse\tIf not p, then not q####Contrapositive\tthe statement formed by negating both the hypothesis and conclusion of the converse of a conditional statement####");

        data.get(1).add(new Deck("Unit 2"));
        data.get(1).get(1).importQuizlet("Deductive Reasoning\tThe process of reasoning logically from given statements or facts to a conclusion####Inductive Reasoning\tThe process of reasoning based on patterns you observe.####Conjecture\tA conclusion you reach using inductive reasoning####Counterexample\tAn example that shows a conjecture or conclusion is false####Conditional statement\tA logical statement that has two parts, a hypothesis and a conclusion####Truth Value\tThe truth or falsity of a statement. IF you can find one counter example, then the truth value of the statement is false.####Negation\tThe opposite of the original statement (~p or ~q)####Converse\tIf q, then p####Inverse\tIf not p, then not q####Contrapostitive\tIf not q, then not p####Logically Equivalent\tstatements that have the same truth values. These include Conditional to contrapositive and converse to inverse.####Biconditional\tp if and only if q####Law of Detachment\tif the hypothesis of a true conditional statement is true, then the conclusion is also true####Law of Syllogism\tIf p-->q and q-->r are true statements, then p-->r is a true statement.####Union Symbol\tU####Intersect Symbol\t∩####Compound Statements\tStatements that involve one or more connectives are compound statements####Addition Property of Equality\tIf a=b, then a+c=b+c####Subtraction Property of Equality\tIf a=b, then a-c=b-c####Multiplication Property of Equality\tIf a=b, then ac=bc####Division Property of Equality\tif a = b and c is not equal to 0, then a/c = b/c####Symmetric Property of Equality\tIf a = b, then b = a####Transitive Property of Equality\tIf a = b and b = c, then a = c####Substitution Property of Equality\tIf a=b, then a may be replaced by b in any equation or expression####Distributive Property of Equality\ta(b+c)=ab+ac or a(b-c)=ab-ac####Reflexive Property of Segment Congruence\tFor any line segment AB, segment AB ≅ segment AB####Symmetric Property of Segment Congruence\tIf line segment AB ≅ line segment CD, then line segment CD ≅ line segment AB####Transitive Property of Segment Congruence\tIf AB ≅ CD and CD ≅ EF, then AB ≅ EF.####Reflexive Property of Angle Congruence\tFor any angle A, ∠A ≅ ∠A.####Symmetric Property of Angle Congruence\tIf ∠A ≅ ∠B, then ∠B ≅ ∠A.####Transitive Property of Angle Congruence\tIf ∠A ≅ ∠B and ∠B ≅ ∠C, then ∠A ≅ ∠C.####Right Angle Congruence Theorem\tAll right angles are congruent####Congruent Supplements Theorem\tIf two angles are supplementary to the same angle (or to congruent angles), then they are congruent.####Congruent Complements Theorem\tIf two angles are complementary to the same angle (or to congruent angles), then they are congruent.####Vertical Angles Theorem\tVertical angles are congruent####Theorem 2-5\tIf two angles are congruent and supplementary, then each is a right angle.####");

        data.get(1).add(new Deck("Unit 3"));
        data.get(1).get(2).importQuizlet("Parallel Lines\tcoplanar lines that do not intersect####Skew Lines\tnon-coplanar lines that do not intersect####Parallel Planes\tplanes that do not intersect####Transversal\ta line that intersects two or more coplanar lines at different points####Corresponding Angles\tlie on the same side of the transversal and in corresponding positions####Alternate Exterior Angles\tnonadjacent exterior angles that lie on opposite sides of the transversal####Alternate Interior Angles\tnonadjacent interior angles that lie on opposite sides of the transversal####consecutive (same side) interior angles\tinterior angles that lie on the same side of the transversal####Corresponding Angles Postulate\tIf two parallel lines are cut by a transversal, then the pairs of corresponding angles are congruent####Alternate Interior Angles Theorem\tIf two parallel lines are cut by a transversal, then the pairs of alternate interior angles are congruent.####Consecutive (SS) Interior Angles Theorem\tIf two parallel lines are cut by a transversal, then the pairs of consecutive interior angles are supplementary.####Alternate Exterior Angles Theorem\tif 2 parallel lines are cut by a transversal, then the pairs of alternate exterior angles are congruent####Corresponding Angles Converse\tIf two lines are cut by a transversal so the corresponding angles are congruent, then the lines are parallel.####Alternate Interior Angles Converse\tIf two lines are cut by a transversal so the alternate interior angles are congruent, then the lines are parallel.####Consecutive Interior Angles Converse\tIf two lines are cut by a transversal so the consecutive interior angles are supplementary, then the lines are parallel.####Alternate Exterior Angles Converse\tIf two lines are cut by a transversal so the alternate exterior angles are congruent, then the lines are parallel.####Equivalent Parallel Lines Theorem (Theorem 3-7)\tIf two lines are parallel to the same line, then they are parallel to each other.####Perpendicular Parallel Lines Theorem (Theorem 3-8)\tIf two lines are perpendicular to the same line, then they are parallel to each other.####Perpendicular Transversal Theorem (Theorem 3-9)\tIn a plane, if a transversal is perpendicular to one of two parallel lines, then it is perpendicular to the other line.####Parallel Postulate (Postulate 3-3)\tThrough a point not on a line, there is one and only one line parallel to the given line.####Triangle Angle Sum Theorem\tThe sum of the measures of the angles of a triangle is 180 degrees.####remote interior angles\tthe two nonadjacent interior angles corresponding to each exterior angle of a triangle####Triangle Exterior Angle Theorem\tThe measure of each exterior angle of a triangle equals the sum of the measures of its two remote interior angles.####Slope\tthe steepness of a line on a graph, equal to its vertical change divided by its horizontal change####Slopes of Parallel Lines\tIn a coordinate plane, two nonvertical lines are parallel if and only if they have the same slope. Any two vertical lines are parallel.####Slopes of Perpendicular Lines\tIn a coordinate plane, two nonvertical lines are perpendicular if and only if the product of their slopes is -1. Horizontal lines are perpendicular to vertical lines.####Exterior Angle of a Polygon\tan angle formed by a side and an extension of an adjacent side####");

        //Adds decks and flashcards to Biology
        data.get(2).add(new Deck("Cells", "Science 7 HNs"));
        data.get(2).get(0).importQuizlet("Cell\tThe smallest unit that is able to preform basic functions of life.####Nucleus\tThe structure in a eukaryotic cell that contains the genetic material a cell needs to reproduce and function.####Cell Membrane\tThe outer boundary of the cytoplasm, a layer that controls what enters and leaves the cell; a protective covering enclosing the cell.####Cell Wall\tA protective outer-covering that lays just outside the membrane of a plant cell.####Cytoplasm\tA thick, gelatin like material contained within the cell membrane. Most of the work of the cell is carried out in the cytoplasm.####Organelle\tA structure in a cell that enclosed by a membrane and preforms a particular function.####Organ\tA structure in a plant or an animal that is made up of different tissues working together to preform a particular function.####Organism\tAn individual living thing, made up of one or many cells, that is capable of growing and reproducing.####Endoplasmic Recticulum (E.R)\tSystem of a membrane channels used for transport within the cells manufactures proteins and part of the cell membrane####Tissue\tA group to cells that are organized to do a specific job.####Organ System\tA group of organs that together preform a function that helps the body meet it's needs for energy and materials.####Chloroplast\tAn organelle in a plant cell where energy from sunlight is used to make sugar during the process of photosynthesis contain green pigments called chlorophyll.####Vacuole\tFluid-filled organelle enclosed by the membrane; holds water, waste, or other materials; works with the cell membrane to move materials into or out of the cell; a plant cell contains a larger central vacuole.####Mitochondrion\tOrganelle in both plant and animal cells that uses oxygen to get energy from food.####");

        data.get(2).add(new Deck("Microscope Science", "Science 7 HNs"));
        data.get(2).get(1).importQuizlet("Arm\tThe arm supports the body above the stage. Always carry a microscope by the arm and base.####Eyepiece\tObjects are viewed through the eye piece. The eye piece contains a lens that commonly magnifies an image 10x.####Base\tThe base supports the microscope.####Body\tSeparates the lens in the eye piece from the objective lenses below####Coarse adjustment knob\tThis knob is used to focus the image on an object when it is viewed through the low-power lens.####Fine adjustment knob\tThis knob is used to focus the image on an object when it is viewed through the high-power lens.####Light/Mirror\tSome microscopes use light that is reflected through the stage by a mirror. Other microscopes have their own light source.####Stage\tThe stage supports the object being viewed.####Stage clip\thold the slides in place on the stage.####Revolving nosepiece\tThe nose piece holds the objective lenses above the stage and rotates so that all lenses my be used.####Diaphragm\tUsed to adjust the amount of light passing through the slide and into an objective lens.####Low-power Objective Lens\tSmallest lens on the nose piece. It magnifies 4x.####Medium-power Objective Lens\tIt magnifies at 10x####High-power Objective Lens\tLargest lens on the nose piece. It magnifies 40x.####");

        JFrame frame = new JFrame();
        JScrollPane treeView = new JScrollPane(data.getTree());
        frame.add(treeView);
        frame.setSize(960, 540);
        frame.setLocation(50, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        data.exportDatabase();
    }
}
