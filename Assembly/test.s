                    .section	.rodata
_printL:            .string	"%d\n"
_readL:             .string	"%d"
                    .text
                    .globl	main
print:                    pushq	%rbp
                    movq 	%rsp, %rbp

                    movl 	%eax, %esi
                    movl 	$_printL, %edi
                    movl 	$0, %eax
                    call 	printf

                    leave
                    ret  
read:                    pushq	%rbp
                    movq 	%rsp, %rbp
                    subq 	$16, %rsp

                    leaq 	-4(%rbp), %rsi
                    movq 	$_readL, %rdi
                    movl 	$0, %eax
                    call 	scanf
                    movl 	-4(%rbp), %eax

                    leave
                    ret  
#main:               NOP  
main:
                    pushq	%rbp
                    movq 	%rsp, %rbp
                    subq 	$28, %rsp

#                    CALL 	read
                    call 	read

#                    STRET	__T0
                    movl 	%eax, -4(%rbp)

#                    MOV  	__T0, __T1
                    movl 	-4(%rbp), %eax
                    movl 	%eax, -8(%rbp)

#                    MOV  	__T1, x
                    movl 	-8(%rbp), %eax
                    movl 	%eax, -28(%rbp)

#                    LT   	x, 0, __L0
                    cmp  	$0, -28(%rbp)
                    jg   	__L0

#                    NEG  	x, __T3
                    movl 	-28(%rbp), %eax
                    neg  	%eax
                    movl 	%eax, -16(%rbp)

#                    MOV  	__T3, __T4
                    movl 	-16(%rbp), %eax
                    movl 	%eax, -20(%rbp)

#                    MOV  	__T4, x
                    movl 	-20(%rbp), %eax
                    movl 	%eax, -28(%rbp)

#__L0:               NOPIF
__L0:

#                    PARAM	x
                    movl 	-28(%rbp), %eax

#                    CALL 	print
                    call 	print

#                    STRET	__T5
                    movl 	%eax, -24(%rbp)

#                    RET  
                    leave
                    retq 
